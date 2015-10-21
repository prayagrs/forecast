<?php
error_reporting(E_PARSE);
 header( 'Content-Type:text/html; charset=UTF-8' );
?>
<html>
<title> Forecast Search </title>
<head>
<style>
.formcenter {
	position: relative;
	left:450px;
	top:120px;
	width: 40%;	
}
.datacenter {
	position: relative;
	left:450px;
	top:120px;
	width: 40%;	
}
</style>
<script>
function sub(){
	var x1 = document.forms["forecast"]["address"].value;
    if (x1 == null || x1 == "") {
        alert("Address must be filled out");
        return false;
    }
    var x2 = document.forms["forecast"]["city"].value;
    if (x2 == null || x2 == "") {
        alert("Please Enter the city name");
        return false;
    }
    var x3 = document.forms["forecast"]["state"].value;
    if (x3 == null || x3 == "") {
        alert("Please Enter name of the State");
        return false;
    }
    var x4 = document.forms["forecast"]["temperature"].value;
    if (x4 == null || x4 == "") {
        alert("Select the temperature type");
        return false;
    }
}


</script>
</head>
<body>
<div class = "formcenter">
<form name = "forecast" method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>" onsubmit="return sub()"
 id ="forecast">
<table>
	<tr>
		<th align="center" style="font-size:30px"> Forecast Search </th>
	</tr>
	<tr>
	<td> Street Address :* </td>
	<td> <input type="text" name="address" <?php if(isset($_POST['address'])){echo 'value="'.$_POST['address'].'"';} ?>></td>
	</tr>
	<tr>
	<td> City :* </td>
	<td> <input type="text" name="city" <?php if(isset($_POST['city'])){echo 'value="'.$_POST['city'].'"';} ?>></td>
	</tr>
	<tr>
	<td> State :* </td>
	<td>
	<select name="state" id="s" <?php if(isset($_POST['state'])){echo 'value="'.$_POST['state'].'"';} ?>>
			<option value=""> </option>
			<option value="AL">Alabama</option>
			<option value="AK">Alaska</option>
			<option value="AZ">Arizona</option>
			<option value="AR">Arkansas</option>
			<option value="CA">California</option>
			<option value="CO">Colorado</option>
			<option value="CT">Connecticut</option>
			<option value="DE">Delaware</option>
			<option value="DC">District of Columbia</option>
			<option value="FL">Florida</option>
			<option value="GA">Georgia</option>
			<option value="HI">Hawaii</option>
			<option value="ID">Idaho</option>
			<option value="IL">Illinois</option>
			<option value="IN">Indiana</option>
			<option value="IA">Iowa</option>
			<option value="KS">Kansas</option>
			<option value="KY">Kentucky</option>
			<option value="LA">Louisiana</option>
			<option value="ME">Maine</option>
			<option value="MD">Maryland</option>
			<option value="MA">Massachusetts</option>
			<option value="MI">Michigan</option>
			<option value="MN">Minnesota</option>
			<option value="MS">Mississippi</option>
			<option value="MO">Missouri</option>
			<option value="MT">Montana</option>
			<option value="NE">Nebraska</option>
			<option value="NV">Nevada</option>
			<option value="NH">New Hampshire</option>
			<option value="NJ">New Jersey</option>
			<option value="NM">New Mexico</option>
			<option value="NY">New York</option>
			<option value="NC">North Carolina</option>
			<option value="ND">North Dakota</option>
			<option value="OH">Ohio</option>
			<option value="OK">Oklahoma</option>
			<option value="OR">Oregon</option>
			<option value="PA">Pennsylvania</option>
			<option value="RI">Rhode Island</option>
			<option value="SC">South Carolina</option>
			<option value="SD">South Dakota</option>
			<option value="TN">Tennessee</option>
			<option value="TX">Texas</option>
			<option value="UT">Utah</option>
			<option value="VT">Vermont</option>
			<option value="VA">Virginia</option>
			<option value="WA">Washington</option>
			<option value="WV">West Virginia</option>
			<option value="WI">Wisconsin</option>
			<option value="WY">Wyoming</option>
		</select>
 		</td>
	</tr>
	<tr>
		<td>Degree :*</td>
		<td><input type="radio" name="temperature" value="us" id="us" checked="checked">Fahrenhiet 
		<input type="radio" name="temperature" value="si" id="si" <?php if($radiobuttonvalue == "degree") { echo 'checked="checked"';} ?>>Degree</td>
	</tr>
	<tr>
	<td></td>
	<td> <input type="submit" name="submit" value="Submit" > 
	 <input type="button" name="clear" value="Clear" onclick="myfunction()"> </td>
	</tr>
	<tr>
		<td> <p> *- Mandatory Fields </p></td>
	</tr>
</table>
<p style="text-align:center;"><a href= "http://forecast.io/">Powered by Forecast.io</a></p>
</form>
</div>
<div class = "datacenter">
<?php
if(isset($_POST['submit'])){
	date_default_timezone_set('America/Los_Angeles');
	
	$streetaddress = $_POST['address'];
	$_city = $_POST['city'];
	$states = $_POST['state'];
	if(isset($_POST['temperature'])){
	 	$tempr = $_POST['temperature'];
	}

	$url= "https://maps.googleapis.com/maps/api/geocode/xml?address=$streetaddress, $_city,$states&key=AIzaSyBo2YILsvJkHGesiM9iVVtfJK0_0ZUI9AA";
	//$url = "http://maps.google.com/maps/api/geocode/xml?address=$streetaddress, $_city,$states";
	$new = str_replace(' ', '%20', $url);

	#$ch = curl_init();
	#curl_setopt($ch, CURLOPT_URL, $new);
	#curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);

	#echo "$ch";
	#$result = curl_exec($ch);

	#echo "$result";
	echo "<br>";

	#curl_close($ch);
	$result = file_get_contents($new);
	$xmlobj = simplexml_load_string($result);

	echo "<br>";echo "<br>";	
	$d= $xmlobj->result->geometry->location->lat;
	$e= $xmlobj->result->geometry->location->lng;

	$url1 = 'https://api.forecast.io/forecast/6438d08d2ebeec01a564731917c35d04/'.$d.','.$e.'?units='.$tempr.'&exclude=flags';
	$contents = file_get_contents($url1);
	$results = json_decode($contents); 
	
	
	$summary = $results->currently->summary;

	$t = $results ->currently->temperature;
	$temp = intval($t);

	$disp = "";
	if( $tempr == "si"){$disp = "&deg;C";}
	if($tempr == "us") {$disp = "&deg;F";}
	

	$i = $results->currently->icon;
	$icon = " ";
	if($i=="clear-day"){$icon = "<img src ='http://cs-server.usc.edu:45678/hw/hw6/images/clear.png' />";}
	if($i=="clear-night"){$icon = "<img src ='http://cs-server.usc.edu:45678/hw/hw6/images/clear_night.png' />";}
	if($i=="rain"){$icon = "<img src ='http://cs-server.usc.edu:45678/hw/hw6/images/rain.png' />";}
	if($i=="snow"){$icon = "<img src ='http://cs-server.usc.edu:45678/hw/hw6/images/snow.png' />";}
	if($i=="sleet"){$icon = "<img src ='http://cs-server.usc.edu:45678/hw/hw6/images/sleet.png' />";}
	if($i=="wind"){$icon = "<img src ='http://cs-server.usc.edu:45678/hw/hw6/images/wind.png' />";}
	if($i=="fog"){$icon = "<img src ='http://cs-server.usc.edu:45678/hw/hw6/images/fog.png' />";}
	if($i=="cloudy"){$icon = "<img src ='http://cs-server.usc.edu:45678/hw/hw6/images/cloudy.png' />";}
	if($i=="partly-cloudy-day"){$icon = "<img src ='http://cs-server.usc.edu:45678/hw/hw6/images/cloud_day.png' />";}
	if($i=="partly-cloudy-night"){$icon = "<img src ='http://cs-server.usc.edu:45678/hw/hw6/images/cloud_night.png' />";}

	$precip = $results->currently->precipIntensity;
	$precipIntensity = " ";
	if($precip >= "0" && $precip<"0.002"){$precipIntensity = " None ";}
	if($precip >= "0.002" && $precip<"0.017"){$precipIntensity = " Very Light";}
	if($precip >= "0.017" && $precip<"0.1"){$precipIntensity = " Light";}
	if($precip >= "0.1" && $precip<"0.4"){$precipIntensity = " Moderate";}
	if($precip >= "0.4"){$precipIntensity = " High";}

	$precipProbability = $results->currently->precipProbability;
	$r = $precipProbability * 100;
	$rain = $r." ". "%"; 

	$wind = $results->currently->windSpeed;
	$windSpeed = $wind." ". "mph";

	$dew = $results->currently->dewPoint;
	$dewPoint = intval($dew);

	$hum = $results->currently->humidity;
	$h = $hum * 100;
	$humidity = $h."%";

	$v = $results->currently->visibility;
	$visible = intval($v)." ". "mi";

	$x = $results->daily->data[0]->sunriseTime;
	#$sunrise=gmdate("h:i a", $x);
	$sunrise =  gmdate("h:i a", $x);

	$y = $results->daily->data[0]->sunsetTime;
	$sunset  = gmdate("h:i a", $y);

	echo "<table>";
	echo "<tr><th style='font-size:25px'> $summary</th></tr><tr><th style='font-size:25px'> $temp $disp </th></tr><tr> <th> $icon </th></tr>";
	echo "<tr><td>Precipitation:</td><td>$precipIntensity</tr>";
	echo "<tr'><td>Chance of Rain:</td><td>$rain</tr>";
	echo "<tr><td>Wind Speed:</td><td>$windSpeed</tr>";
	echo "<tr><td>Dew Point:</td><td>$dewPoint</tr>";
	echo "<tr><td>Humidity:</td><td>$humidity</tr>";
	echo "<tr><td>Visibilty:</td><td>$visible</tr>";
	echo "<tr><td>Sunrise:</td><td>$sunrise</tr>";
	echo "<tr><td>Sunset:</td><td>$sunset</tr>";
	echo "</table>";
}
?>
</div>
<script>
document.getElementById('s').value = "<?php echo $_POST['state'];?>";

function myfunction() {
	
	document.getElementById("forecast").reset();
}

</script>
</body>
</html>
