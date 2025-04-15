<%@ page import="model.Test" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Test Management</title>
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
<a href="${pageContext.request.contextPath}/admin/tests" class="block font-semibold text-blue-600">Manage Tests</a>
                <a href="${pageContext.request.contextPath}/admin/manageAssessments.jsp" class="block hover:text-blue-600">Manage Assessments</a>
                <a href="${pageContext.request.contextPath}/admin/reports.jsp" class="block hover:text-blue-600">Reports</a>
            </nav>
        </aside>

        <!-- Main content -->
        <main class="flex-1 p-8 bg-gray-50">
            <h1 class="text-2xl font-bold mb-6">Test Management</h1>

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

            <!-- Create Test Form -->
            <div class="bg-white p-6 rounded shadow-md mb-8">
                <h2 class="text-xl font-bold mb-4">Create New Test</h2>
<form action="<%= request.getContextPath() %>/admin/createTest" method="post">                    <div>
                        <label class="block font-medium">Test Title</label>
                        <input type="text" name="title" class="w-full mt-1 border rounded p-2" required>
                    </div>

                  <!-- In the form section -->
                  <div class="grid grid-cols-2 gap-4">
                      <div>
                          <label class="block font-medium">Recruiter</label>
                        <select name="recruiterId" class="w-full mt-1 border rounded p-2" required>
                            <c:forEach items="${recruiters}" var="recruiter">
                                <option value="${recruiter.id}">${recruiter.name}</option>
                            </c:forEach>
                        </select>
                      </div>

                      <div>
                          <label class="block font-medium">Assessment</label>
                          <select name="assessmentId" class="w-full mt-1 border rounded p-2" required>
                              <c:forEach items="${assessments}" var="assessment">
                                  <option value="${assessment.id}">${assessment.name}</option>
                              </c:forEach>
                          </select>
                      </div>
                  </div>
                    </div>

                    <div>
                        <label class="block font-medium">Target Difficulty (1-10)</label>
                        <input type="number" name="targetDifficulty" min="1" max="10"
                               class="w-full mt-1 border rounded p-2" value="5" required>
                    </div>

                    <button type="submit" class="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700">
                        Create Test
                    </button>
                </form>
            </div>

            <!-- Tests List -->
            <h2 class="text-xl font-bold mb-4">All Tests</h2>
            <div class="bg-white p-4 rounded shadow-md overflow-x-auto">
                <!-- Tests Table -->
                <table class="w-full text-left">
                    <thead>
                        <tr class="border-b">
                            <th class="py-2">ID</th>
                            <th>Title</th>
                            <th>Recruiter</th>
                            <th>Assessment</th>
                            <th>Difficulty</th>
                            <th>Created Date</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${empty tests}">
                                <tr>
                                    <td colspan="7" class="text-center py-4">No tests found</td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${tests}" var="test">
                                <tr class="border-b hover:bg-gray-50">
                                    <td class="py-2">${test.id}</td>
                                    <td>${test.title}</td>
                                    <td>
                                        <c:forEach items="${recruiters}" var="recruiter">
                                            <c:if test="${recruiter.id == test.recruiterId}">
                                                ${recruiter.name}
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                    <td>
                                        <c:forEach items="${assessments}" var="assessment">
                                            <c:if test="${assessment.id == test.assessmentId}">
                                                ${assessment.name}
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                    <td>${test.targetDifficulty}</td>
                                    <td>${test.createdDate}</td>
                                    <td>
                                        <!-- Edit/Delete buttons -->
                                    </td>
                                </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>
        </main>
    </div>
</body>
</html>