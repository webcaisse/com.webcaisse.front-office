<#import "spring.ftl" as spring />
<html>
<head>
<link rel="stylesheet" type="text/css" href="<@spring.url '/css/style.css' />" />
<title>Login</title>
</head>
<body>
<h2 id="sc" class="webCaisseTitle">WEBCAISSE</h2>
<div id="box">
<form method="POST" action="<@spring.url '/postlogin' />">
<table>
    <tr>
      <td align="right"><p id="text">Pseudo:</p></td>
      <td align="left">
		<input type="text" name="login">
	</td>
    </tr>
    <tr>
      <td align="right"><p id="text">mot de passe:</p></td>
      <td align="left"><input type="password" name="password"></td>
    </tr>
    <tr>
      <td align="right"><input type="submit" value="connexion"></td>
      <td align="left"><input type="reset" value="annuler"></></td>
    </tr>
</table>
</form>
</div> 
</body>
</html>
