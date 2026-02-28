# Questions

Here are 2 questions related to the codebase. There's no right or wrong answer - we want to understand your reasoning.

## Question 1: API Specification Approaches

When it comes to API spec and endpoints handlers, we have an Open API yaml file for the `Warehouse` API from which we generate code, but for the other endpoints - `Product` and `Store` - we just coded everything directly. 

What are your thoughts on the pros and cons of each approach? Which would you choose and why?

**Answer:**
```txt
OpenAPI-first provides strong contract governance, better documentation, and is ideal for external or enterprise APIs.
Code-first provides faster development and flexibility, ideal for internal services.
For long-term scalability and consistency, I prefer OpenAPI-first, but I would balance it based on service criticality and team size.
```

---

## Question 2: Testing Strategy

Given the need to balance thorough testing with time and resource constraints, how would you prioritize tests for this project? 

Which types of tests (unit, integration, parameterized, etc.) would you focus on, and how would you ensure test coverage remains effective over time?

**Answer:**
```txt
I prioritize unit tests for business logic because they provide the highest ROI in speed and defect prevention. 
Then I focus on integration tests for critical workflows involving persistence and external communication. 
I limit end-to-end tests to only the most business-critical flows.
To ensure effectiveness over time, I enforce CI coverage thresholds, review tests during code review, and use mutation testing to validate test quality. 
I focus more on meaningful coverage of critical paths rather than chasing high coverage percentages.
```
