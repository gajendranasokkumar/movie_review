<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Admin Dashboard - Movie Review System</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
	<nav class="bg-white shadow-lg">
		<div class="max-w-7xl mx-auto px-4">
			<div class="flex justify-between h-16">
				<div class="flex">
					<div class="flex-shrink-0 flex items-center">
						<h1 class="text-xl font-bold">Movie Review System</h1>
					</div>
				</div>
				<div class="flex items-center">
					<a href=<%=request.getContextPath() + "/auth/logout"%>
						class="text-gray-600 hover:text-gray-900"> Logout </a>
				</div>
			</div>
		</div>
	</nav>

	<div class="max-w-7xl mx-auto py-6 sm:px-6 lg:px-8">
		<div class="px-4 py-6 sm:px-0">
			<div id="movieForm" class="bg-white shadow rounded-lg p-6 mb-6">
				<h2 class="text-lg font-medium mb-4">Add New Movie</h2>
				<form action="movies" method="post">
					<div class="grid grid-cols-1 gap-6">
						<div>
							<label class="block text-sm font-medium text-gray-700">
								Movie Name </label> <input type="text" name="name"
								class="mt-1 block w-full border rounded-md shadow-sm p-2">
						</div>
						<div>
							<label class="block text-sm font-medium text-gray-700">
								Description </label>
							<textarea name="description" rows="3"
								class="mt-1 block w-full border rounded-md shadow-sm p-2">
                            </textarea>
						</div>
						<div>
							<label class="block text-sm font-medium text-gray-700">
								Release Date </label> <input type="date" name="releaseDate"
								class="mt-1 block w-full border rounded-md shadow-sm p-2">
						</div>
						<button
							class="bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600">
							Add Movie</button>
					</div>
				</form>
			</div>

			<div id="movieList" class="bg-white shadow rounded-lg p-6">
				<h2 class="text-lg font-medium mb-4">Movie List</h2>
				<div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">

				</div>
			</div>
		</div>
	</div>

	<script>
        // Load movies on page load
        fetch('http://localhost:8080/wad-java/movies')
            .then(response => response.json())
            .then(movies => {
                const movieList = document.querySelector('#movieList > div');
                movieList.innerHTML = movies.map(movie => `
                    <div class="bg-gray-50 rounded-lg p-4">
                        <h3 class="font-medium">${movie.name}</h3>
                        <p class="text-sm text-gray-600">${movie.description}</p>
                        <p class="text-sm text-gray-500">Release Date: ${movie.releaseDate}</p>
                        <p class="text-sm text-gray-500">Average Rating: ${movie.averageRating.toFixed(1)}</p>
                    </div>
                `).join('');
            });

        // Handle form submission
        document.getElementById('addMovieForm').addEventListener('submit', (e) => {
            e.preventDefault();
            const formData = new FormData(e.target);
            
            fetch('http://localhost:8080/wad-java/movies', {
                method: 'POST',
                body: formData
            }).then(() => {
                location.reload();
            });
        });
    </script>
</body>
</html>