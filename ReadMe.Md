<span style="font-size: 30px;"><center>**Dotnet CICD Pipeline**</center></span>

This repository contains the configuration for an automated CI/CD pipeline using Azure DevOps. The pipeline is designed to build, test, and deploy a .NET application to an Azure Kubernetes Service (AKS) cluster.

**Pipeline Overview**:

The pipeline consists of the following stages:

1. PR Validation Stage: This stage executes when a pull request is initiated. It involves testing the .NET application.

2. Continuous Integration (CI) Stage: This stage runs for changes other than pull requests. It builds and pushes the Docker image of the application to an Azure Container Registry (ACR).

3. Continuous Deployment (CD) Stage: In this stage, the application is deployed to an AKS cluster if both PR Validation and CI stages are successful.


**Pipeline Configuration**:

1. Agent Pool-
            The pipeline utilizes the 'SonarQube Integration Agent Pool' for executing jobs.
2. Variables-
            The pipeline includes templates for defining variables related to applications, ACR, AKS, and SonarQube configurations.
3. Jobs-
            Jobs are specified in separate YAML templates for tasks such as testing the .NET application, building and pushing Docker images, and deploying to AKS.
4. Stages-
            Each stage is conditionally triggered based on factors like whether it's a pull request or not, as well as on the outcomes of preceding stages.
5. Deployment Parameters-
            When deploying to AKS in the CD stage, parameters including AKS resource group, Kubernetes cluster name, subscription information, and ACR details are provided to ensure successful deployment.


**How to Use**

1. Copy the DotNet folder from Nexus repository and copy some common templates from common folder.
2. Configure your Azure DevOps project with appropriate service connections for ACR and AKS.
3. Adjust variables within each template according to your environment's requirements.
4. Initiate pipeline runs by pushing changes or opening pull requests within your forked repository.
5. Additional instructions on how to use each template are included within the template itself.
