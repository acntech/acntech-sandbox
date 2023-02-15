export interface ClientError {
    cause: string,
    redirectUrl: string
}

export interface ClientResponse<T> {
    status: number,
    data?: T,
    error?: ClientError
}

export async function GET<T>(url: string): Promise<ClientResponse<T>> {
    const response = await fetch(url, {method: "GET", redirect: "manual"});
    const {type, status} = response;
    if (type === 'opaqueredirect') {
        console.log("AUTHENTICATION REDIRECT");
        window.location.pathname = '/oauth2/authorization/acntech-generic-client';
        window.location.hash = '';
    } else if (status === 200) {
        const data = await response.json();
        return {status, data}
    } else {
        return {status}
    }
}
