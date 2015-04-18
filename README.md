# Ultra Rapid Build
The Riot Games API Challenge - Ultra Rapid Build
--------------
This is my submission for the Riot Games API challenge.

This Android application gets the popular builds for each champion in the featured game mode Ultra Rapid Fire.

The way the items were calculated was by iterating through a large set of static data in the Data.java file. There is limited data in the Data.java file so the items are not 100% accurate because of the limit with my API key. With more data in the Data.java file, the more accurate the items will be.

It is based on whether the champion won, and if they won, what items did they buy. The reason for this is to ensure the effectiveness of the application to filter out unaccurate items. Players can use this application to help decide what items to buy for the champion they selected in Ultra Rapid Fire since they will no longer be playing the meta and they will get different items.

**NOTE:** The asset folder is not committed to this repo because it contains my API key and because of security reasons. There is a "apikey.txt" file in the asset folder with the API key in the text in order for the code to run.

Here are some screenshots/demos of how my application looks like:
- https://www.dropbox.com/s/oqrrbq6pnucxltq/UltraRapidBuild%20-%20Menu.png?dl=0
- https://www.dropbox.com/s/ndc0u5orgl03ye9/UltraRapidBuild%20-%20Champ.png?dl=0

