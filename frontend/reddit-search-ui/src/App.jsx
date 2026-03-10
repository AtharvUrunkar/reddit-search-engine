import { useState, useEffect } from "react";
import axios from "axios";

function App() {

  const [query, setQuery] = useState("");
  const [posts, setPosts] = useState([]);
  const [page, setPage] = useState(0);
  const [loading, setLoading] = useState(false);
  const [searchTime, setSearchTime] = useState(null);

  const search = async (reset = true) => {

    if (!query || loading) return;

    setLoading(true);

    const startTime = performance.now();

    const response = await axios.get(
      `http://localhost:8080/search?q=${query}&page=${reset ? 0 : page}&size=10`
    );

    const endTime = performance.now();
    const timeTaken = ((endTime - startTime) / 1000).toFixed(3);

    setSearchTime(timeTaken);

    const data = response.data.content;

    if (reset) {
      setPosts(data);
      setPage(1);
    } else {
      setPosts(prev => [...prev, ...data]);
      setPage(prev => prev + 1);
    }

    setLoading(false);
  };

  const highlight = (text) => {

    if (!query) return text;

    const regex = new RegExp(`(${query})`, "gi");

    return text.replace(regex, "<mark class='bg-yellow-300'>$1</mark>");
  };

  useEffect(() => {

    const handleScroll = () => {

      if (loading) return;

      if (
        window.innerHeight + document.documentElement.scrollTop
        >= document.documentElement.offsetHeight - 200
      ) {
        search(false);
      }

    };

    window.addEventListener("scroll", handleScroll);

    return () => window.removeEventListener("scroll", handleScroll);

  }, [page, query, loading]);

  return (

    <div className="min-h-screen bg-gray-100 p-10">

      <h1 className="text-4xl font-bold text-center mb-8">
        Reddit Search Engine
      </h1>

      <div className="flex justify-center mb-6">

        <input
          type="text"
          placeholder="Search Reddit posts..."
          className="border p-3 w-96 rounded-l"
          value={query}
          onChange={(e) => setQuery(e.target.value)}
        />

        <button
          className="bg-blue-500 text-white px-6 rounded-r hover:bg-blue-600"
          onClick={() => search(true)}
        >
          Search
        </button>

      </div>

      {searchTime && (

        <p className="text-center text-gray-500 mb-6">
          Results found in {searchTime} seconds
        </p>

      )}

      <div className="grid gap-5 max-w-3xl mx-auto">

        {posts.map((post) => (

          <a
            key={post.id}
            href={`https://www.reddit.com/r/${post.subreddit}/comments/${post.redditPostId}`}
            target="_blank"
            rel="noopener noreferrer"
            className="block"
          >

            <div className="bg-white p-5 rounded shadow flex gap-4 hover:bg-gray-50 cursor-pointer transition">

              {post.thumbnail &&
                post.thumbnail.startsWith("http") && (

                <img
                  src={post.thumbnail}
                  alt="thumbnail"
                  className="w-24 h-24 object-cover rounded"
                />

              )}

              <div>

                <h2
                  className="text-lg font-semibold"
                  dangerouslySetInnerHTML={{
                    __html: highlight(post.title)
                  }}
                />

                <p className="text-gray-600 text-sm">
                  r/{post.subreddit} • Score {post.score}
                </p>

                <p className="text-gray-400 text-sm">
                  by {post.author}
                </p>

                <p className="text-blue-500 text-sm mt-2">
                  Open on Reddit →
                </p>

              </div>

            </div>

          </a>

        ))}

      </div>

      {loading && (

        <div className="text-center mt-6 text-gray-500">
          Loading more results...
        </div>

      )}

    </div>

  );
}

export default App;