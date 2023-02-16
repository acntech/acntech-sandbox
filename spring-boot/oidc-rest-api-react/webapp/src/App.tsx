import React, {FC, ReactElement, useEffect, useReducer} from "react";
import {createBrowserRouter, Outlet, RouterProvider} from "react-router-dom";
import {userInitialState, userReducer} from "./state/reducers";
import {User} from "./types";
import Header from "./fragments/Header";
import Footer from "./fragments/Footer";
import Home from "./pages/Home";
import About from "./pages/About";
import NotFound from "./pages/NotFound";
import {GET} from "./state/client";

interface LayoutProps {
    userInfo: User
}

const Layout: FC<LayoutProps> = (props: LayoutProps): ReactElement => (
    <>
        <Header userInfo={props.userInfo}/>
        <Outlet/>
        <Footer/>
    </>
);

const App: FC = (): ReactElement => {
    const [userState, userDispatch] = useReducer(userReducer, userInitialState);

    useEffect(() => {
        GET<User>("/api/user")
            .then(response => userDispatch({type: 'SUCCESS', data: response.data}))
            .catch(error => {
                console.log("ERROR", error)
                userDispatch({type: 'FAILED'})
            });
    }, []);

    const router = createBrowserRouter([
        {
            element: <Layout userInfo={userState.data}/>,
            children: [
                {
                    path: "/",
                    element: <Home/>,
                },
                {
                    path: "/about",
                    element: <About/>,
                },
                {
                    path: "*",
                    element: <NotFound/>,
                }
            ]
        }
    ]);

    return <RouterProvider router={router}/>;
};

export default App;