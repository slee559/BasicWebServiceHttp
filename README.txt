1. pom file is used to build, deploy and test the web service. 
   The dependency includes JAX-RS, JSON, JAXB, Jetty, Logging, JUnit, Joda, H2 and etc.

2. The Logger is used to give information on the process of the web service also to debugging purpose. For example, steps of the test and printing errors.

3. Test cases are developed mainly to show the communication. Also, the JPA persistence.
	- GET method with xml is tested
	- GET method with json is tested
	- POST with xml is tested
	- POST with json is tested
	- PathParam is tested
	- QueryParam is tested
	- CookieParam is tested
	- MatrixParam is tested
	- HeaderParam is tested
	- PUT with xml is test, but not json, because the POST includes the function of PUT.

4. ORM-based persistence is part of the test. UPDATE, POST, and GET is used to access the database. 
   The XML is converted into entity object to be persisted.

5. XML is converted into object, and the object is converted into XML by JAXB and the mappers.

6. Domain model is relatively complex as the full parolee.

7. REST web design is used.
	- addressable resources: Every resource is reached uniquely given the URIs provided on the class anotatons.
	- uniform constrained interface: GET, PUT, and POST is used to make sure it is uniform.
	- representation-oriented: the web service can deal with xml and json.
	- stateless communication: no internal database to store data, only cookie is used.

8. HHTP protocols that are leveraging are used as listed above in the 3.

9. The web service provides asynchronous processes. 
   It returns a vehicle of the given owner's id. 
   It was first designed to get the list of vehicles, but for the simplicity it just returns 1 vehicle. 
   And also, it caused lots of errors. It was not easy to get objects from the Elements that are mapped using XmlID and XmlIDREF.
   It returned null. To make sure the stateless communication, no other internal database is used.

10. The HTTP protocols can consume and produce both xml or json. This was done by using maven dependency that is resteasy-jackson-provider.
