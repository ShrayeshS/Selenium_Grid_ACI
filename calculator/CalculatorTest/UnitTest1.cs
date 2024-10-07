using calculator.Controllers;
using calculator.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using Moq;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace CalculatorTest
{
   public class UnitTest1
    {
        [Fact]
        public void Index_Post_Addition_ReturnsViewWithCorrectResult()
        {
            // Arrange
            Mock<ILogger<HomeController>> loggerMock = new Mock<ILogger<HomeController>>();
            HomeController controller = new HomeController(loggerMock.Object);
            Calculator cal = new Calculator { value1 = 10, value2 = 5, calculate = "add" };
            string myVariable = "15";
            // Act
            ViewResult? result = controller.Index(cal) as ViewResult;

            // Assert
            Xunit.Assert.NotNull(result);
            Xunit.Assert.Equal(myVariable, result.ViewData["result"]);
        }
        /*
        [Fact]
        public void Index_Post_Addition_ReturnsViewWithCorrectResult_Float()
        {
            // Arrange
            Mock<ILogger<HomeController>> loggerMock = new Mock<ILogger<HomeController>>();
            HomeController controller = new HomeController(loggerMock.Object);
            Calculator cal = new Calculator { value1 = 10.1, value2 = 5.1, calculate = "add" };
            string myVariable = "15.2";
            // Act
            ViewResult? result = controller.Index(cal) as ViewResult;

            // Assert
            Xunit.Assert.NotNull(result);
            Xunit.Assert.Equal(myVariable, result.ViewData["result"]);
        }

        [Fact]
        public void Index_Post_Addition_ReturnsViewWithCorrectResult_Negative()
        {
            // Arrange
            Mock<ILogger<HomeController>> loggerMock = new Mock<ILogger<HomeController>>();
            HomeController controller = new HomeController(loggerMock.Object);
            Calculator cal = new Calculator { value1 = -10, value2 = -5, calculate = "add" };
            string myVariable = "-15";
            // Act
            ViewResult? result = controller.Index(cal) as ViewResult;

            // Assert
            Xunit.Assert.NotNull(result);
            Xunit.Assert.Equal(myVariable, result.ViewData["result"]);
        } 
        */
        [Fact]
        public void Index_Sub()
        {
            // Arrange
            Mock<ILogger<HomeController>> loggerMock = new Mock<ILogger<HomeController>>();
            HomeController controller = new HomeController(loggerMock.Object);
            Calculator cal = new Calculator { value1 = 10, value2 = 5, calculate = "sub" };
            string myVariable = "5";
            // Act
            ViewResult? result = controller.Index(cal) as ViewResult;

            // Assert
            Xunit.Assert.NotNull(result);
            Xunit.Assert.Equal(myVariable, result.ViewData["result"]);
        }

        [Fact]
        public void Index_mul()
        {
            // Arrange
            Mock<ILogger<HomeController>> loggerMock = new Mock<ILogger<HomeController>>();
            HomeController controller = new HomeController(loggerMock.Object);
            Calculator cal = new Calculator { value1 = 10, value2 = 5, calculate = "mul" };
            string myVariable = "50";
            // Act
            ViewResult? result = controller.Index(cal) as ViewResult;

            // Assert
            Xunit.Assert.NotNull(result);
            Xunit.Assert.Equal(myVariable, result.ViewData["result"]);
        }

        [Fact]
        public void Index_divi()
        {
            // Arrange
            Mock<ILogger<HomeController>> loggerMock = new Mock<ILogger<HomeController>>();
            HomeController controller = new HomeController(loggerMock.Object);
            Calculator cal = new Calculator { value1 = 10, value2 = 5, calculate = "divi" };
            string myVariable = "2";
            // Act
            ViewResult? result = controller.Index(cal) as ViewResult;

            // Assert
            Xunit.Assert.NotNull(result);
            Xunit.Assert.Equal(myVariable, result.ViewData["result"]);
        }

        [Fact]
        public void Index_Post_Addition_ReturnsViewWithCorrectResult__()
        {
            // Arrange
            Mock<ILogger<HomeController>> loggerMock = new Mock<ILogger<HomeController>>();
            HomeController controller = new HomeController(loggerMock.Object);
            Calculator cal = new Calculator { value1 = 10, value2 = 5, calculate = "add" };
            string myVariable = "15";

            // Act
            ViewResult? result = controller.Index(cal) as ViewResult;

            // Assert
            NUnit.Framework.Legacy.ClassicAssert.NotNull(result);
            NUnit.Framework.Legacy.ClassicAssert.AreEqual(myVariable, result.ViewData["result"]);
        }

        [Fact]
        public void Index_Sub_()
        {
            // Arrange
            Mock<ILogger<HomeController>> loggerMock = new Mock<ILogger<HomeController>>();
            HomeController controller = new HomeController(loggerMock.Object);
            Calculator cal = new Calculator { value1 = 10, value2 = 5, calculate = "sub" };
            string myVariable = "5";

            // Act
            ViewResult? result = controller.Index(cal) as ViewResult;

            // Assert
            NUnit.Framework.Legacy.ClassicAssert.NotNull(result);
            NUnit.Framework.Legacy.ClassicAssert.AreEqual(myVariable, result.ViewData["result"]);
        }

        [Fact]
        public void Index_mul__()
        {
            // Arrange
            Mock<ILogger<HomeController>> loggerMock = new Mock<ILogger<HomeController>>();
            HomeController controller = new HomeController(loggerMock.Object);
            Calculator cal = new Calculator { value1 = 10, value2 = 5, calculate = "mul" };
            string myVariable = "50";

            // Act
            ViewResult? result = controller.Index(cal) as ViewResult;

            // Assert
            NUnit.Framework.Legacy.ClassicAssert.NotNull(result);
            NUnit.Framework.Legacy.ClassicAssert.AreEqual(myVariable, result.ViewData["result"]);
        }
        [Fact]
        public void Index_divi__()
        {
            // Arrange
            Mock<ILogger<HomeController>> loggerMock = new Mock<ILogger<HomeController>>();
            HomeController controller = new HomeController(loggerMock.Object);
            Calculator cal = new Calculator { value1 = 10, value2 = 5, calculate = "divi" };
            string myVariable = "2";

            // Act
            ViewResult? result = controller.Index(cal) as ViewResult;

            //Assert
            NUnit.Framework.Legacy.ClassicAssert.NotNull(result);
            NUnit.Framework.Legacy.ClassicAssert.AreEqual(myVariable, result.ViewData["result"]);
        }
        
        [Fact]
        public void Index_Sub__()
         {
         // Arrange
         Mock<ILogger<HomeController>> loggerMock= new Mock<ILogger<HomeController>>();
         HomeController controller=new HomeController(loggerMock.Object);
         Calculator cal=new Calculator{value1=10,value2=5,calculate="sub"};
         string myVariable="5";
         
         // Act 
         ViewResult result=controller.Index(cal) as ViewResult;
         
         //Assert 
         Microsoft.VisualStudio.TestTools.UnitTesting.Assert.IsNotNull(result); 
         Microsoft.VisualStudio.TestTools.UnitTesting.Assert.AreEqual(myVariable,result.ViewData["result"]); 
        }
        
        
        [Fact]
        public void Index_Post_Addition_ReturnsViewWithCorrectResult_()
        {
        // Arrange
        Mock<ILogger<HomeController>> loggerMock = new Mock<ILogger<HomeController>>();
        HomeController controller = new HomeController(loggerMock.Object);
        Calculator cal = new Calculator { value1 = 10, value2 = 5, calculate = "add" };
        string myVariable = "15";

        // Act
        ViewResult result = controller.Index(cal) as ViewResult;

        // Assert
        Microsoft.VisualStudio.TestTools.UnitTesting.Assert.IsNotNull(result);
        Microsoft.VisualStudio.TestTools.UnitTesting.Assert.AreEqual(myVariable, result.ViewData["result"]);
        }

        [Fact]
        public void Index_mul_()
        {
          // Arrange
          Mock<ILogger<HomeController>> loggerMock=new Mock<ILogger<HomeController>>();
          HomeController controller=new HomeController(loggerMock.Object);
          Calculator cal=new Calculator{value1=10,value2=5,calculate="mul"};
          string myVariable="50";
          
          // Act 
          ViewResult result=controller.Index(cal) as ViewResult;
          
          //Assert 
          Microsoft.VisualStudio.TestTools.UnitTesting.Assert.IsNotNull(result); 
          Microsoft.VisualStudio.TestTools.UnitTesting.Assert.AreEqual(myVariable,result.ViewData["result"]); 
        }

        [Fact]
        public void Index_divi_()
        {
           // Arrange
           Mock<ILogger<HomeController>> loggerMock=new Mock<ILogger<HomeController>>();
           HomeController controller=new HomeController(loggerMock.Object);
           Calculator cal=new Calculator{value1=10,value2=5,calculate="divi"};
           string myVariable="2";
           
           // Act 
           ViewResult result=controller.Index(cal) as ViewResult;
           
           //Assert 
           Microsoft.VisualStudio.TestTools.UnitTesting.Assert.IsNotNull(result); 
           Microsoft.VisualStudio.TestTools.UnitTesting.Assert.AreEqual(myVariable,result.ViewData["result"]); 
        }
      

    }

}
