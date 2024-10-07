 using Xunit;

 namespace HelloWorldApp.Tests
 {
     public class HelloWorldTests
     {
         [Fact]
         public void TestGetGreeting()
         {
             string greeting = HelloWorld.GetGreeting();
             Assert.Equal("Hello, World!", greeting);
         }
     }
 }
