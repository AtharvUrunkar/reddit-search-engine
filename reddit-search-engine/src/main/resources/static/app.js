async function search() {

    const query = document.getElementById("searchInput").value;

    const response = await fetch(`/search?q=${query}&page=0&size=10`);

    const data = await response.json();

    const resultsDiv = document.getElementById("results");

    resultsDiv.innerHTML = "";

    data.content.forEach(post => {

        const div = document.createElement("div");
        div.className = "result";

        div.innerHTML = `
            <h3>${post.title}</h3>
            <p><b>Subreddit:</b> r/${post.subreddit}</p>
            <p><b>Author:</b> ${post.author}</p>
            <p><b>Score:</b> ${post.score}</p>
        `;

        resultsDiv.appendChild(div);

    });

}