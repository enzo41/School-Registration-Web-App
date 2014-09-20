School-Registration-Web-App
===========================

Simple web app to demonstrate Object-Oriented Design, Test-Driven Development, GoF and Enterprise Design Patterns, Servlets, JSP, JSTL and JDBC. **[The use cases and class diagram can be found here.](https://docs.google.com/document/d/1KTiuKl44UfQUF1-vzyP9LJBXseoGpBLz-niyqOvSmEE/edit?usp=sharing)**

## An Opinionated Approach

Notice that I have eschewed interfaces for the Services and DAOs. I've never encountered a project where you needed more than one implementation for each Service or each DAO. I think the Service-ServiceImpl pattern is a vestige of the EJB2 days. The only purpose I used to see for the interfaces was to create mock objects, back in the days when there were no mocking frameworks. However, now that we have mocking frameworks, that reason is gone, too. Just don't make your methods final so they can be mocked!

