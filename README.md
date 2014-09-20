School-Registration-Web-App
===========================

Simple web app to demonstrate Object-Oriented Design, Test-Driven Development, GoF and Enterprise Design Patterns, Servlets, JSP, JSTL and JDBC. **[The use cases and class diagram can be found here.](https://docs.google.com/document/d/1KTiuKl44UfQUF1-vzyP9LJBXseoGpBLz-niyqOvSmEE/edit?usp=sharing)**

## Look Ma, No Interfaces!

Notice that I have eschewed Interfaces for the Services and DAOs. I've never encountered a project where you needed more than one implementation for each Service or each DAO. I think the Service-ServiceImpl pattern is a vestige of the EJB2 days. The only purpose I used to see for the interfaces was to create mock objects, back in the days when there were no mocking frameworks. However, now that we have mocking frameworks, that reason is gone, too. Just don't make your methods final so they can be mocked!

## Layer Supertype

The Entity class is the [Layer Supertype](http://martinfowler.com/eaaCatalog/layerSupertype.html) for all the domain entities. It's sole job is to hold a private key, to map each entity to a row in the database. This is set and get in the DAOs through Reflection. Hibernate uses Reflection as well to set and get final fields.

## Super-Simple Authentication

I created a very simple authentication component just to demonstrate Filters. This is not meant to be a recommended approach. Creating an effective, secure authentication framework is very, very difficult. For real systems, better rely on an established framework like Spring Security.

