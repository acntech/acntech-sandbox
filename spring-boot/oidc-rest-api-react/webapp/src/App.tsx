import React, {FC, ReactElement, useEffect, useReducer} from "react";
import {createBrowserRouter, Outlet, RouterProvider} from "react-router-dom";
import {userInfoInitialState, userInfoReducer} from "./state/reducers";
import {UserInfo} from "./types/user-info";
import Header from "./fragments/Header";
import Footer from "./fragments/Footer";
import Home from "./pages/Home";
import About from "./pages/About";
import NotFound from "./pages/NotFound";
import {GET} from "./state/client";

interface LayoutProps {
    userInfo: UserInfo
}

const Layout: FC<LayoutProps> = (props: LayoutProps): ReactElement => (
    <>
        <Header userInfo={props.userInfo}/>
        <Outlet/>
        <Footer/>
    </>
);

const App: FC = (): ReactElement => {
    const [userInfoState, userInfoDispatch] = useReducer(userInfoReducer, userInfoInitialState);

    useEffect(() => {
        GET<UserInfo>("/api/userinfo")
            .then(response => userInfoDispatch({type: 'SUCCESS', data: response.data}))
            .catch(error => {
                console.log("ERROR", error)
                userInfoDispatch({type: 'FAILED'})
            });
    }, []);

    const router = createBrowserRouter([
        {
            element: <Layout userInfo={userInfoState.data}/>,
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