package com.accenture.automatizacion.testing;

import org.testng.annotations.Test;

import com.accenture.automatizacion.datadrive.DataDrivenUsers;
import com.accenture.automatizacion.dto.Credenciales;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

import org.junit.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;


public class test_calculadora {
  
    public static AppiumDriver<WebElement> driver;
    
    DesiredCapabilities capabilities = new DesiredCapabilities();
    
    @BeforeMethod
	public void setUpAppium() throws MalformedURLException, InterruptedException{
    	/*APK INFO:com.google.android.calculator2.Calculator
     	         * com.google.android.calculator2.CalculatorSecurity*/
        String packagename = "com.linkedin.android";
        String URL="http://127.0.0.1:4723/wd/hub";
        String activityname = "com.linkedin.android.authenticator.LaunchActivity";
    	DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "device");
//		capabilities.setCapability("udid", "3100719eb68c2400");
		capabilities.setCapability("udid", "7N2UNB159P002533");
		capabilities.setCapability("platformVersion", "6.0");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", packagename);
		capabilities.setCapability("appActivity", activityname);
		driver = new AndroidDriver<WebElement>(new URL(URL), capabilities);
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
    	
	  }

	@AfterTest
	  public void CleanUpAppium() {
		
		driver.quit();}
	
    @Test
    public void mytest() throws InterruptedException {
    	
    	try {
    		int tam = 3;
    		int opcion = 0;
    		Credenciales credencial = new Credenciales();
			Assert.assertNotNull(driver.getContext());
			//Toma el archivo de excel donde estan las cuentas y los usuarios a ingresar
    		DataDrivenUsers dataDrivenUsers = new DataDrivenUsers("C://Users/Administrator/Desktop/Automator.xlsx");
    		
    		WebElement sin_opcion = driver.findElement(By.id("com.google.android.gms:id/cancel"));
			sin_opcion.click();
			//Thread.sleep(1000);
			int rowNum = -1;
    		int ban;
    		String FinalStatus = "";
    		ban = 0;
	    	while(opcion <= tam){
				rowNum++;
				System.out.println("Fila = "+(rowNum+1));
	    		
	    		//credencial = dataDrivenUsers.getCellData(rowNum, FinalStatus);
	    		credencial = dataDrivenUsers.getCellData(rowNum);
	    		//Toma el nombre de la cuenta
	    		String nombre = credencial.getUsername();
	    		//Toma el password de la cuenta
	    		String pass = credencial.getPassword();
		        if (ban == 0){
	    		WebElement inicio = driver.findElement(By.id("com.linkedin.android:id/growth_prereg_fragment_sign_in_button") );
		        inicio.click();		        
		        sin_opcion.click();
		        }
		        WebElement inicio_sesion = driver.findElement(By.id("com.linkedin.android:id/growth_login_join_fragment_email_address"));
		        inicio_sesion.click();
		        inicio_sesion.clear();
		        //Ingreso de nombre de usuario
		        inicio_sesion.sendKeys(nombre+"@gmail.com");
		        
		        WebElement password = driver.findElement(By.id("com.linkedin.android:id/growth_login_join_fragment_password"));
		        password.click();  
		        
		        //Ingreso de contraseña
		        password.sendKeys(pass);
		     // Wait for launced app to load
		        //Thread.sleep(1000);
		        
		        WebElement enter = driver.findElement(By.id("com.linkedin.android:id/growth_login_fragment_sign_in"));
		        enter.click(); 
		        Thread.sleep(8000);
		        
		        //Inicio de sesion
		        if(inicio_sesion.isDisplayed())//coloca true si todavia esta en la pantalla inicio_sesion
		        {
		        	//
//		        	FinalStatus = "Rechazado";
		        	System.out.println("Contraseña incorrecta");
					dataDrivenUsers.setStatus(rowNum,"C://Users/Administrator/Desktop/automator.xlsx",FinalStatus );
//					dataDrivenUsers.setStatus(rowNum,"C://Users/Administrator/Desktop/automator.xlsx" );
			        ban = 1;
		        }
		        else
		        {	
		        	ban = 0;
		        	System.out.println("Contraseña correcta");
		        	System.out.println("EL usuario "+ inicio_sesion + " ha iniciado sesion en Linkedin");
//		        	switch(opcion)
//					{
//					case 0:   
//						fcn1();
//						break;
//					case 1:
//						fcn1();
//						break;
//					case 2:
//						fcn1();
//						break;
//					case 3:
//						fcn1();
//						break;
//					case 4:
//						fcn1();
//						break;
//					default:
//						break;
//					}
		        	
			        WebElement personal = driver.findElement(By.id("com.linkedin.android:id/me_launcher"));
			        personal.click();
			        //Thread.sleep(1000);
			        System.out.println("Opcion = "+opcion);
			        System.out.println("Fila = "+rowNum);
			        
//			        Random r = new Random();
//			        opcion = r.nextInt(4);
					switch(opcion)
					{
					case 0:   
						fcn1();
						break;
					case 1:
						fcn2();
						break;
					case 2:
						fcn3();
						break;
					case 3:
						fcn4();
						break;
					case 4:
						fcn5();
						break;
					default:
						break;
					}
			        
			        WebElement ajustes = driver.findElement(By.id("com.linkedin.android:id/profile_toolbar_settings_button"));
			        ajustes.click();
			        Thread.sleep(1000);
			        driver.swipe(300, 600, 300, 400, 2000);   
			        WebElement cerrar_sesion = driver.findElement(By.xpath("//android.widget.LinearLayout[@bounds = '[0,970][600,1023]']"));
			        cerrar_sesion.click();
			        
		        }    
		        opcion++;
		        if (ban == 0)
		        {	
		        	FinalStatus = "Aceptado";
		        	
		        	
		        }else
		        {
		        	FinalStatus = "Rechazado";
		        }	
		        System.out.println(FinalStatus);
		        dataDrivenUsers.setStatus(rowNum,"C://Users/Administrator/Desktop/automator.xlsx",FinalStatus);
//		        dataDrivenUsers.setStatus(rowNum,"C://Users/Administrator/Desktop/automator.xlsx");
	    	}
	    	System.out.println("Opcion: "+opcion);
	    	System.out.println("Tam: "+tam);
//      driver.sendKeyEvent(AndroidKeyCode.HOME);
//  	Thread.sleep(3000);
//  	// Press Menu button to view all installed app's
//  	driver.findElementByAccessibilityId("Apps").click();
      
        //inicio_sesion = driver.findElement(By.xpath(" "));
        //inicio_sesion.click();
        
        //Thread.sleep(1000);
//        driver.manage().window().getSize();
//        	int x = (int) (dimens.getWidth() * 0.5);
//        	int startY = (int) (dimens.getHeight() * 0.5);
//        	int endY = (int) (dimens.getHeight() * 0.2);
//        	driver.swipe(x, startY, x, endY, 800);
         //WebElement cortar = driver.findElement(By.id("android:id/cut"));
        //cortar.click();
        
//        inicio_sesion.click();
        
//        //
//        WebElement equals = driver.findElement(By.id("com.android.calculator2:id/equal"));
//        equals.click();
//        outputBox = driver.findElement(By.className("android.widget.EditText"));
//        String result = outputBox.getText();
//        int actual = 1+9;
//        //Assert.assertEquals(String.valueOf(actual),result);
    	} catch (Exception e) {
//    		System.out.print("\n\t Se presento Excepción " + e);
		}
     }
    
    //Funcion que coloca foto de perfil desde una foto localizada en la galeria
    public void fcn1() throws InterruptedException {
    	System.out.println("El usuario colocará foto de perfil");
		WebElement photo = driver.findElement(By.id("com.linkedin.android:id/profile_view_top_card_profile_picture"));
        photo.click();    
        WebElement tomar_photo = driver.findElement(By.xpath("//android.widget.TextView[@bounds = '[80,532][568,580]']"));
        //WebElement tomar_photo = driver.findElement(By.id("com.linkedin.android:id/profile_edit_photo_select_item_name"));		        
        tomar_photo.click(); 
        Thread.sleep(2000); 
        WebElement galery_photo = driver.findElement(By.id("com.android.documentsui:id/icon_thumb"));
        galery_photo.click();
        WebElement save_photo = driver.findElement(By.id("com.linkedin.android:id/profile_edit_toolbar_save"));
        save_photo.click();
        Thread.sleep(1000);   	
    }
    
    //Funcion que elimina la foto de perfil
    public void fcn2() throws InterruptedException {
    	System.out.println("El usuario eliminará foto de perfil");
    	WebElement photo = driver.findElement(By.id("com.linkedin.android:id/profile_view_top_card_profile_picture"));
        photo.click();
        Thread.sleep(1000);      
        WebElement delete_photo = driver.findElement(By.id("com.linkedin.android:id/profile_edit_photo_delete"));
        delete_photo.click();
        WebElement borrar_photo = driver.findElement(By.id("android:id/button1"));
        borrar_photo.click();
    }
  //Funcion que ingresa la educacion en el perfil
    public void fcn3() throws InterruptedException {
    	System.out.println("El usuario ingresará educación");
    	WebElement nivel_perfil = driver.findElement(By.id("com.linkedin.android:id/profile_completion_meter_container"));
    	nivel_perfil.click();  
        WebElement perfil_educacion = driver.findElement(By.xpath("//android.widget.TextView[@text = 'educación']"));
//    	WebElement perfil_educacion = driver.findElement(By.xpath("//android.widget.LinearLayout[@index = '1']"));
//    	.findElement(By.xpath("//android.widget.TextView [@text = 'Configuración y privacidad']"));
//        WebElement perfil_educacion = driver.findElement(By.xpath("//android.widget.TextView[@text = 'educación']"));

        perfil_educacion.click(); 
        WebElement insert_educacion = driver.findElement(By.id("com.linkedin.android:id/identity_guided_edit_education_school_name"));
        insert_educacion.click();
        WebElement letra_educacion = driver.findElement(By.id("com.linkedin.android:id/search_bar_text"));
        letra_educacion.click();
        letra_educacion.sendKeys("University of Phoenix"); 
        WebElement university_educacion = driver.findElement(By.id("com.linkedin.android:id/type_ahead_large_view_first_text"));
        university_educacion.click();
        WebElement saltar1_educacion = driver.findElement(By.id("com.linkedin.android:id/identity_guided_edit_skip_button"));
        saltar1_educacion.click();        
        WebElement saltar2_educacion = driver.findElement(By.id("com.linkedin.android:id/identity_guided_edit_skip_button"));
        saltar2_educacion.click(); 
        WebElement saltar3_educacion = driver.findElement(By.id("com.linkedin.android:id/identity_guided_edit_skip_button"));
        saltar3_educacion.click(); 
        WebElement final_educacion = driver.findElement(By.id("com.linkedin.android:id/guided_edit_continue_button"));
        final_educacion.click();
        driver.navigate().back();
        
    }
    
    //Funcion para seguir a una persona
    public void fcn4() throws InterruptedException {
    	System.out.println("El usuario seguira a una persona");
    	WebElement photo = driver.findElement(By.id("com.linkedin.android:id/profile_view_top_card_profile_picture"));
        photo.click();
        Thread.sleep(1000);      
        WebElement tomar_photo = driver.findElement(By.xpath("//android.widget.LinearLayout[@bounds = '[80,532][568,580]']"));
        tomar_photo.click();        
        Thread.sleep(3000);
        WebElement galery_photo = driver.findElement(By.id("com.android.documentsui:id/icon_thumb"));
        galery_photo.click();
        Thread.sleep(1000);
        WebElement save_photo = driver.findElement(By.id("com.linkedin.android:id/profile_edit_toolbar_save"));
        save_photo.click();
        Thread.sleep(7000);    	
    }
    
    //Funcion que realiza un comentario
    public void fcn5() throws InterruptedException {
    	System.out.println("El usuario realizará un comentario");
    	WebElement photo = driver.findElement(By.id("com.linkedin.android:id/profile_view_top_card_profile_picture"));
        photo.click();
        Thread.sleep(1000);      
        WebElement tomar_photo = driver.findElement(By.xpath("//android.widget.LinearLayout[@bounds = '[80,532][568,580]']"));
        tomar_photo.click();        
        Thread.sleep(3000);
        WebElement galery_photo = driver.findElement(By.id("com.android.documentsui:id/icon_thumb"));
        galery_photo.click();
        Thread.sleep(1000);
        WebElement save_photo = driver.findElement(By.id("com.linkedin.android:id/profile_edit_toolbar_save"));
        save_photo.click();
        Thread.sleep(7000);    	
    }
}