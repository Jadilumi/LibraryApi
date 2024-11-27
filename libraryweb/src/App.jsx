import Loading from "./components/Loading/index.jsx";
import {Route, Routes} from "react-router-dom";
import PublicRoute from "./service/auth/PublicRoute/index.jsx";
import NotFound from "./components/NotFound/index.jsx";
import Login from "./pages/Login/index.jsx";
import NavBar from "./components/Navbar/index.jsx";
import Dashboard from "./pages/Dashboard/index.jsx";
import Book from "./pages/Book/index.jsx";
import Client from "./pages/Client/index.jsx";
import Loan from "./pages/Loan/index.jsx";
import Fine from "./pages/Fine/index.jsx";
import Profile from "./pages/Profile/index.jsx";
import Setting from "./pages/Setting/index.jsx";
import Report from "./pages/Report/index.jsx";
import BookForm from "./pages/Book/BookForm/index.jsx";
import LoanForm from "./pages/Loan/LoanForm/index.jsx";

function App() {
    return (
        <div className={`h-screen flex flex-col`}>
            <Routes>
                <Route path={"/"} element={
                    <PublicRoute>
                        <Login/>
                    </PublicRoute>
                }/>

                <Route path={"/in/dashboard"} element={
                    <PublicRoute>
                        <NavBar/>
                        <Dashboard/>
                    </PublicRoute>
                }/>

                <Route path={"/in/books"} element={
                    <PublicRoute>
                        <NavBar/>
                        <Book/>
                    </PublicRoute>
                }/>

                <Route path={"/in/books/add"} element={
                    <PublicRoute>
                        <NavBar/>
                        <BookForm/>
                    </PublicRoute>
                }/>

                <Route path={"/in/books/edit/:bookId"} element={
                    <PublicRoute>
                        <NavBar/>
                        <BookForm/>
                    </PublicRoute>
                }/>

                <Route path={"/in/clients"} element={
                    <PublicRoute>
                        <NavBar/>
                        <Client/>
                    </PublicRoute>
                }/>

                <Route path={"/in/loans/add"} element={
                    <PublicRoute>
                        <NavBar/>
                        <LoanForm/>
                    </PublicRoute>
                }/>

                <Route path={"/in/loans/edit/:bookId/:loanId"} element={
                    <PublicRoute>
                        <NavBar/>
                        <LoanForm/>
                    </PublicRoute>
                }/>

                <Route path={"/in/fines"} element={
                    <PublicRoute>
                        <NavBar/>
                        <Fine/>
                    </PublicRoute>
                }/>

                <Route path={"/in/reports"} element={
                    <PublicRoute>
                        <NavBar/>
                        <Report/>
                    </PublicRoute>
                }/>

                <Route path={"/in/profile"} element={
                    <PublicRoute>
                        <NavBar/>
                        <Profile/>
                    </PublicRoute>
                }/>

                <Route path={"/in/settings"} element={
                    <PublicRoute>
                        <NavBar/>
                        <Setting/>
                    </PublicRoute>
                }/>
            </Routes>
        </div>
    )
}

export default App
