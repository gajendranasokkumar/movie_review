<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Login - Movie Review System</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
	<div class="min-h-screen flex items-center justify-center">
		<div class="bg-white p-8 rounded-lg shadow-md w-96">
			<h2 class="text-2xl font-bold mb-6">Login</h2>

			<c:if test="${param.error != null}">
				<div
					class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-4">
					Invalid username or password</div>
			</c:if>

			<form action="auth" method="post" >
				<div class="mb-4">
					<label class="block text-gray-700 font-bold mb-2" for="username">
						Username </label> <input class="w-full px-3 py-2 border rounded-lg"
						type="text" name="username" id="username" required>
				</div>

				<div class="mb-6">
					<label class="block text-gray-700 font-bold mb-2" for="password">
						Password </label> <input class="w-full px-3 py-2 border rounded-lg"
						type="password" name="password" id="password" required>
				</div>

				<button
					class="w-full bg-blue-500 text-white py-2 px-4 rounded-lg hover:bg-blue-600">
					Sign In</button>
			</form>
		</div>
	</div>
</body>
</html>