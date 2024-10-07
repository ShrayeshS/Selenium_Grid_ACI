#See https://aka.ms/customizecontainer to learn how to customize your debug container and how Visual Studio uses this Dockerfile to build your images for faster debugging.

FROM mcr.microsoft.com/dotnet/aspnet:8.0 AS base
USER root
WORKDIR /app
EXPOSE 8080
EXPOSE 8081

FROM mcr.microsoft.com/dotnet/sdk:8.0 AS build
USER root
ARG BUILD_CONFIGURATION=Release
WORKDIR /src
#COPY replace-env-var.sh .
#RUN chmod +x /src/replace-env-var.sh
COPY ["calculator/calculator/calculator.csproj", "calculator/"]
RUN dotnet restore "./calculator/calculator.csproj"
COPY . .
#RUN /src/replace-env-var.sh
WORKDIR "/src/calculator"
RUN dotnet build "./calculator/calculator.csproj" -c $BUILD_CONFIGURATION -o /app/build

FROM build AS publish
USER root
ARG BUILD_CONFIGURATION=Release
RUN dotnet publish "./calculator/calculator.csproj" -c $BUILD_CONFIGURATION -o /app/publish /p:UseAppHost=false

FROM base AS final
USER root
WORKDIR /app
COPY --from=publish /app/publish .

ENTRYPOINT ["dotnet", "calculator.dll"]
#ENTRYPOINT ["/bin/bash", "-c", "/app/replace-env-var.sh && dotnet calculator.dll"]
