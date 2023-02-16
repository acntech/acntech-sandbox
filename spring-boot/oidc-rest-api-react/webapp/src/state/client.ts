import {ClientResponse} from "../types";

const authenticationLoginPath = '/oauth2/authorization/acntech-generic-client';

export async function GET<T>(url: string): Promise<ClientResponse<T>> {
    const response = await fetch(url, {method: "GET", redirect: "manual"});
    const {type, status} = response;
    if (type === 'opaqueredirect') {
        console.log("AUTHENTICATION REDIRECT");
        window.location.pathname = authenticationLoginPath;
        window.location.hash = '';
        return {status: 302}
    } else if (status === 401) {
        console.log("AUTHENTICATION FAILED");
        window.location.pathname = authenticationLoginPath;
        window.location.hash = '';
        return {status}
    } else if (status === 200) {
        const data = await response.json();
        return {status, data}
    } else {
        return {status}
    }
}
