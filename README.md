My Retail App
=============

myRetail is a rapidly growing company with HQ in Richmond, VA and over 200 stores across the east coast. myRetail wants to make its internal data available to any number of client devices, from myRetail.com to native mobile apps. 

Local Deployment:
 
  * Configure application.properties file with Local Mongo DB configuration uri or host/port and username/password if need to be authenticated
  * Start application with VM options
  
> >Exposes below Rest End points 
> >    * GET /products/{id} -> Retrieves Product Details for a given product id
> >    * GET /products/name/{id} -> Retrieves Product Name for a given product id
> >    * PUT /products/{id} --> Updates product details(price) for a given product id
  * PostMan collection json file can be found here --> **myRetail/src/test/resources/static/myRetail.postman_collection.json**        

Run Tests: Configure **test** DB configuration in test application.properties file.
 * **gradle clean test**
 
Install Mongo DB and setting up Collections and Product Data on Mac
 
 * Install Mongo DB --> **brew install mongodb**
 * Setup Data folder --> **mkdir -p /data/db**
 * Give permissions --> **sudo chown -R `id -un` /data/db**
 * Run the Mongo Deamon inone terminal --> **mongod** 
 * Run the Mongo shell in another terminal--> **mongo**
 *  Create a Database called myRetail --> **use myRetail**
 * Create ProductName and ProductPrice Collections with below data
   >
   >>db.productPrice.insertMany([{	"productid" : 15117729,
   >   >            "currentprice" : {	"value" : 13.49,	"currencycode" : "USD"	}
   >   >             },{            "productid" : 16483589,         "currentprice" : {
   >   >                        "value" : 295.49,             "currencycode" : "USD"
   >   >                    }     },{         "productid" : 16696652,
   >   >                    "currentprice" : {             "value" : 95.49,
   >   >                        "currencycode" : "USD"         }     },{
   >   >                    "productid" : 16752456,         "currentprice" : {
   >   >                        "value" : 9.49,             "currencycode" : "USD"
   >   >                    }     },{         "productid" : 15643793,
   >   >                    "currentprice" : {             "value" : 19.49,
   >   >                        "currencycode" : "USD"         }     }])
   > 
   > 
   >>db.productName.insertMany([{	"productid" : 15117729,
   >   >             "productname" : "The Big Lebowski (Blu-ray) (Widescreen)"     },
   >   >                  {         "productid" : 16483589,
   >   >                      "productname" : "Graco 4-1 Convertible Swing"     },     {
   >   >                      "productid" : 16696652,
   >   >                       "productname" : "Graco SnugRide"     },     {
   >   >                      "productid" : 16752456,
   >   >                      "productname" : "SwaddleMe Original 0-3M"     },     {
   >   >                      "productid" : 15643793,
   >   >                      "productname" : "Fisher Price Toy"     }])
