package controllers;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.user_info;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class rootWindowController implements Initializable
{
	@FXML PasswordField user_pw;
	@FXML TextField user_id;
	@FXML Button login;
	@FXML Button register;
	private user_info ui = null;
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		ui = new user_info();
		login.setOnAction(event->login());
		register.setOnAction(event->signup());
	}
	public void login()
	{
		try {
			if(ui.login(user_id.getText(), user_pw.getText()))
			{
				System.out.println("logined");
				Parent login_ = FXMLLoader.load(getClass().getResource("/view/main.fxml"));// �ҷ�����
			    Scene scene = new Scene(login_);
			    Stage primaryStage = (Stage)login.getScene().getWindow(); // ���� ������ ��������
			    primaryStage.setScene(scene);
			}
			else
				System.out.println("login failed");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("�α��� ����");
		}
	}
	public void signup()
	{
		try
		{
		    Parent login = FXMLLoader.load(getClass().getResource("/view/signup.fxml"));//ȸ������â �ҷ�����
		    Scene scene = new Scene(login);
		    Stage primaryStage = (Stage)register.getScene().getWindow(); // ���� ������ ��������
		    primaryStage.setScene(scene);
		 } 
		catch(Exception e1)
		{
		       e1.printStackTrace();
		       System.out.println("FXML�ҷ����� ����");
		}
	}

}
