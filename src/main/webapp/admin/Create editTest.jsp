<%@ page import="model.Test" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Test</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
    <div class="flex h-screen">
        <!-- Sidebar -->
        <aside class="w-64 bg-white border-r shadow-sm">
            <div class="p-4 text-xl font-bold border-b">Quizify</div>
            <nav class="p-4 space-y-4">
                <a href="${pageContext.request.contextPath}/admin/dashboard.jsp" class="block hover:text-blue-600">Dashboard</a>
                <a href="${pageContext.request.contextPath}/admin/manageUsers.jsp" class="block hover:text-blue-600">Manage Users</a>
                <a href="${pageContext.request.contextPath}/admin/manageTests.jsp" class="block font-semibold text-blue-600">Manage Tests</a>
                <a href="${pageContext.request.contextPath}/admin/manageAssessments.jsp" class="block hover:text-blue-600">Manage Assessments</a>
                <a href="${pageContext.request.contextPath}/admin/reports.jsp" class="block hover:text-blue-600">Reports</a>
            </nav>
        </aside>

        <!-- Main content -->
        <main class="flex-1 p-8 bg-gray-50">
            <h1 class="text-2xl font-bold mb-6">Edit Test</h1>

            <!-- Success/Error Messages -->
            <c:if test="${not empty successMessage}">
                <div class="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded relative mb-4" role="alert">
                    <span class="block sm:inline">${successMessage}</span>
                </div>
            </c:if>
            <c:if test="${not empty errorMessage}">
                <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative mb-4" role="alert">
                    <span class="block sm:inline">${errorMessage}</span>
                </div>
            </c:if>

            <!-- Edit Test Form -->
            <div class="bg-white p-6 rounded shadow-md">
                <form action="${pageContext.request.contextPath}/admin/editTest" method="post" class="space-y-4">
                    <input type="hidden" name="id" value="${test.id}">

                    <div>
                        <label class="block font-medium">Test Title</label>
                        <input type="text" name="title" value="${test.title}" class="w-full mt-1 border rounded p-2" required>
                    </div>

                    <div class="grid grid-cols-2 gap-4">
                        <div>
                            <label class="block font-medium">Recruiter</label>
                            <select name="recruiterId" class="w-full mt-1 border rounded p-2" required>
                                <option value="2" ${test.recruiterId == 2 ? 'selected' : ''}>Recruiter Max</option>
                            </select>
                        </div>

                        <div>
                            <label class="block font-medium">Assessment</label>
                            <select name="assessmentId" class="w-full mt-1 border rounded p-2" required>
                                <option value="1" ${test.assessmentId == 1 ? 'selected' : ''}>Java Basics</option>
                                <option value="2" ${test.assessmentId == 2 ? 'selected' : ''}>Web Fundamentals</option>
                            </select>
                        </div>
                    </div>

                    <div>
                        <label class="block font-medium">Target Difficulty (1-10)</label>
                        <input type="number" name="targetDifficulty" min="1" max="10"
                               value="${test.targetDifficulty}" class="w-full mt-1 border rounded p-2" required>
                    </div>

                    <div class="flex space-x-4">
                        <button type="submit" class="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700">
                            Update Test
                        </button>
                        <a href="${pageContext.request.contextPath}/admin/manageTests.jsp"
                           class="bg-gray-600 text-white px-4 py-2 rounded hover:bg-gray-700">
                            Cancel
                        </a>
                    </div>
                </form>
            </div>
        </main>
    </div>
</body>
</html>