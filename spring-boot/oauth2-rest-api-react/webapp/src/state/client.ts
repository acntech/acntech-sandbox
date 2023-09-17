import {ClientResponse} from "../types";

export async function GET<T>(url: string): Promise<ClientResponse<T>> {
    const response = await fetch(url, {method: "GET", redirect: "manual"});
    const {status} = response;
    if (status === 401) {
        window.location.pathname = response.headers.get("Location");
        window.location.hash = '';
        return {status}
    } else if (status === 200) {
        const data = await response.json();
        return {status, data}
    } else {
        return {status}
    }
}
