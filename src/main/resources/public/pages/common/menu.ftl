	<div layout="row">
		<div class="top-bar ng-scope">
			<md-content>
				<md-toolbar class="md-hue-2">
					<div class="md-toolbar-tools">
						<md-button class="md-icon-button" ng-href="index" <#if page == "index">ng-disabled="true"</#if> style="width:100px">
							<img ng-src="img/icons/mood.png" height="26" />
						</md-button>

						<h2 hide-sm flex md-truncate style="padding-top:5px;">Hello Openshift</h2>

						<#if user ?? && user.user_name ??>
							<#if roles ?? && roles?seq_contains("artist")><md-button ng-href="artists">Artists</md-button></#if>
							<#if roles ?? && roles?seq_contains("movie")><md-button ng-href="movies">Movies</md-button></#if>
							<md-button ng-controller="LogoutController" ng-click="logout()">Logout</md-button>
						<#else>
							<md-button class="md-icon-button" aria-label="More" ng-href="login">
								<img ng-src="img/icons/admin-login.png" height="26" />
							</md-button>
						</#if>
					</div>
				</md-toolbar>
			</md-content>
		</div>	
	</div>
