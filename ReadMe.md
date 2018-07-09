
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Product Price Management
-------------------------------------------------------------------------------------------------------------------------------------------------------

Deployment Instructions

	1) Replace the credentials of mongoDB, OAuth Server details, OAuth credentials in the application.properties file
	2) From command prompt navigate to the folder where the pom.xml file located
	3) Run the command mvn clean package
	4) Jar will be created in the folder ./target/**.jar
	5) Run the jar by the command java -jar target/**.jar
	6) In case of IDE, run the project as spring boot app
____________________________________________________________________________________________________________________________________________________________________________

Configuration

port no : 8080 (configurable in application.properties)

MongoDB : This api connects to the mongoDB cloud (Atlas). The Connection string is configurable in application.properties (can be replaced with onprem DB as well)

OAuth : This Service uses OKTA as an Oauth provider to authenticate the users. Configurations can be found at application.properties 
________________________________________________________________________________________________________________________________________________________________________

Getting Access token from Okta

Run the following CURL command

curl -X POST \
  'https://dev-804238.oktapreview.com/oauth2/default/v1/token?grant_type=client_credentials&scope=myretail' \
  -H 'authorization: Basic MG9hZm9yNG9vbUZraDJPRVIwaDc6YUtPSUtmR1l0U1hpUDVsRWFGxxxxxxxBDV1lQNURwSGxPVXdKWg==' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/x-www-form-urlencoded' \
  -H 'postman-token: 3016a9fb-70bc-6bf0-1060-ba88e19daf6e'
  
  authorization header is Basic (base64encode(clientId:clientSecret)
  
  the response will contain the access_token (JWT)
________________________________________________________________________________________________________________________________________________________________________

This API exposes the following endpoints

1) GET /products/prices
	
Description : This will respond with the complete set of all products available with its price details

Required Headers :
	Accept = "application/json"
	Authorization = "Bearer -accessToken-"
________________________________________________________________________________________________________________________________________________________________________

2) GET /products/prices/"productId"
	
Description : This will respond with the price details of the product with id "productId"

Required Headers :
	Accept = "application/json"
	Authorization = "Bearer -accessToken-"
________________________________________________________________________________________________________________________________________________________________________

3) POST /products/prices
	
Description : This will create a new product with its price details

Required Headers :
	Accept = "application/json"
	Authorization = "Bearer -accessToken-"
	Content-Type = "application/json"

Request Body :
	{
        "id": 9099,
        "product_desc": "Shoes",
        "current_price": {
            "value": 11.05,
            "currency_code": "INR"
        }
    }
_____________________________________________________________________________________________________________________________________________________________________
4) PUT /products/prices
	
Description : This will update a existing product's price details

Required Headers :
	Accept = "application/json"
	Authorization = "Bearer -accessToken-"
	Content-Type = "application/json"

Request Body :
	{
        "id": 9099,
        "product_desc": "Shoes",
        "current_price": {
            "value": 11.05,
            "currency_code": "INR"
        }
    }
_____________________________________________________________________________________________________________________________________________________________________
5) DELETE /products/prices/"productId"
	
Description : This will delete the product price details of the product with an Id "productId"

Required Headers :
	Authorization = "Bearer -accessToken-"
________________________________________________________________________________________________________________________________________________________________________
