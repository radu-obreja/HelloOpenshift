<!DOCTYPE html>
<html>
<head>
	<#include "common/head.ftl">
	<#include "common/authorities.ftl">
</head>
<body ng-app="hello" ng-cloak>

	<#include "common/menu.ftl">

	<div layout="row" ng-controller="ArtistController">
		<div flex="5"></div>
		<div flex="75">
			<h2 md-truncate>Artists</h2>
			<md-list ng-cloak>
				<md-list-item ng-repeat="item in artists">{{$index+1}}. {{ item.artist_name }}</md-list-item>
			</md-list>
		</div>
		<div flex="20">
			<h2 md-truncate>Roles</h2>
			<md-list ng-cloak>
				<md-list-item ng-repeat="item in roles">{{$index+1}}. {{ item }}</md-list-item>
			</md-list>
		</div>
	</div>
	
	<#include "common/scripts.ftl">
	<script src="js/controllers/artists.js"></script>
	
</body>
</html>
