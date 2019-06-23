# RESTful FacilityManagement

A simple RESTful Web Service backed by a mySQL database.
Made with Java EE 8, using JPA (Hibernate), JAX-RS(Jersey) and JAXB.

The resources are buildings, their rooms, and the rooms' sensors.
The web service can be categorized as a Level 3 RESTful web service, according to the Richardson Maturity model. This means that providing only the service's starting point should be enough to navigate through the web service, due to the implementation of the "Hypermedia As The Engine Of Application State (HATEOAS)"-principle.

The web services starting point is:
https://localhost:8080/RESTfulFacilityManagement/webapi/buildings
