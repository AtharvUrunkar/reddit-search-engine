
# Reddit Search Engine

A full-stack search engine that indexes Reddit posts from multiple subreddits and allows users to search them using **PostgreSQL Full-Text Search with GIN indexing**.

This project was built to explore how **search engines index, rank, and retrieve text data efficiently**.

---


# Features

* Crawl posts from multiple Reddit subreddits
* Store and index posts in PostgreSQL
* Full-Text Search using `tsvector` and `tsquery`
* Fast text search using **GIN indexes**
* Ranking of results using `ts_rank`
* Infinite scrolling search results
* Keyword highlighting in results
* Post thumbnails and metadata
* Clickable results that redirect to the original Reddit post
* Search latency display (shows query time)

---

# Tech Stack

## Backend

* Java
* Spring Boot
* PostgreSQL
* REST APIs
* JPA / Hibernate

## Frontend

* React
* TailwindCSS
* Axios

## Data Source

* Reddit API

---

# System Architecture

```
Reddit API
     ↓
Crawler Service (Spring Boot)
     ↓
PostgreSQL Database
     ↓
Full-Text Search Index (GIN)
     ↓
Search API
     ↓
React Frontend
```

---

# How Search Works

The project uses **PostgreSQL Full-Text Search**.

Text fields are converted into a searchable vector:

```
tsvector
```

User queries are converted into:

```
tsquery
```

Matching posts are retrieved using:

```
search_vector @@ plainto_tsquery(...)
```

Results are ranked using:

```
ts_rank(search_vector, query)
```

A **GIN index** is used to significantly speed up search queries.

---

# Project Structure

```
reddit-search-engine
│
├── backend
│   ├── controller
│   ├── services
│   ├── repository
│   ├── model
│   └── config
│
├── frontend
│   ├── src
│   ├── components
│   └── App.jsx
│
└── README.md
```

---

# Installation

## 1. Clone the Repository

```
git clone https://github.com/YOUR_USERNAME/reddit-search-engine.git
```

```
cd reddit-search-engine
```

---

## 2. Run Backend

Navigate to backend folder:

```
cd backend
```

Run the Spring Boot application:

```
mvn spring-boot:run
```

Backend runs on:

```
http://localhost:8080
```

---

## 3. Run Frontend

Navigate to frontend folder:

```
cd frontend
```

Install dependencies:

```
npm install
```

Start the development server:

```
npm run dev
```

Frontend runs on:

```
http://localhost:5173
```

---

# Example Search Request

```
GET /search?q=java&page=0&size=10
```

Example response:

```
{
  "content": [
    {
      "title": "Spring Boot Performance Tips",
      "subreddit": "java",
      "score": 245
    }
  ]
}
```

---

# Future Improvements

* Autocomplete search suggestions
* Better ranking algorithm
* Search filters by subreddit
* Caching layer (Redis)
* Advanced crawling strategies

---

# What I Learned

Building this project helped me understand:

* How **search engines index text data**
* How **full-text search works in databases**
* Designing **scalable backend APIs**
* Building a **crawler pipeline for data ingestion**

---

