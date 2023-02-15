import React, {FC, ReactElement} from "react";
import {Link} from "react-router-dom";
import {Nav, Navbar, NavDropdown} from "react-bootstrap";
import {LinkContainer} from "react-router-bootstrap";
import Logo from "../images/AcnTech-MenuLogo.png";
import {UserInfo} from "../types/user-info";

const defaultUserInfo: UserInfo = {
    sub: 'N/A',
    name: 'N/A'
}

interface HomeProps {
    userInfo: UserInfo
}

const Header: FC<HomeProps> = (props: HomeProps): ReactElement => {
    const {name} = props.userInfo || defaultUserInfo;

    return (
        <header className="header mb-5">
            <Navbar bg="dark" variant="dark" expand="lg" className="p-4">
                <Navbar.Brand>
                    <Link to="/">
                        <img src={Logo} alt="AcnTech Logo"/>
                    </Link>
                </Navbar.Brand>
                <Nav className="ms-auto">
                    <LinkContainer to="/">
                        <Nav.Link className="px-3">Home</Nav.Link>
                    </LinkContainer>
                    <LinkContainer to="/about">
                        <Nav.Link className="px-3">About</Nav.Link>
                    </LinkContainer>
                    <NavDropdown title="Profile" className="px-3" menuVariant="dark">
                        <NavDropdown.Item disabled={true}>{name}</NavDropdown.Item>
                        <NavDropdown.Divider/>
                        <NavDropdown.Item href="/logout">Logout</NavDropdown.Item>
                    </NavDropdown>
                </Nav>
            </Navbar>
        </header>
    );
};

export default Header;