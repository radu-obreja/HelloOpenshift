<!DOCTYPE html>
<html>
<head>
	<#include "common/head.ftl">
	<#include "common/authorities.ftl">
</head>
<body ng-app="hello" ng-cloak>

	<#include "common/menu.ftl">

	<div layout="row" style="height:60px;">
	</div>	

	<div layout="column" layout-align="center center" ng-controller="LoginController">
		<div layout="column" class="login-box">
			<md-toolbar style="background: rgba(0, 0, 0, 0.5);">
				<h2 class="md-toolbar-tools"><span>Login</span></h2>
			</md-toolbar>
			<form name="hoLoginForm">
				<md-content layout="column" class="md-padding">
					<md-input-container>
						<label>Username</label>
						<input required type="text" ng-model="hoUsername" name="hoUserInput" autocomplete="off" />
						<div ng-messages="hoLoginForm.hoUserInput.$error">
							<div ng-message="required">This is required.</div>
						</div>
					</md-input-container>
					<md-input-container>
						<label>Password</label>
						<input required type="password" ng-model="hoPassword" name="hoPassInput" autocomplete="off" />
						<div ng-messages="hoLoginForm.hoPassInput.$error" role="alert">
							<div ng-message="required">This is required.</div>
						</div>
					</md-input-container>
					<div layout="row" layout-align="center center">
						<div flex="flex"></div>
						<md-button ng-click="authenticate()" class="md-raised md-primary">Login</md-button>
						<div flex="flex"></div>
					</div>
				</md-content>
			</form>
		
		</div>
	</div>
	
	<#include "common/scripts.ftl">
	<script src="js/controllers/login.js"></script>
	
</body>
</html>
