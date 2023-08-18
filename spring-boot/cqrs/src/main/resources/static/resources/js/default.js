const getUTCEpocMillis = () => {
    const date = new Date();
    return Date.UTC(date.getUTCFullYear(), date.getUTCMonth(),
        date.getUTCDate(), date.getUTCHours(),
        date.getUTCMinutes(), date.getUTCSeconds());
}

let lastPollMillis = getUTCEpocMillis();

const updateGreetings = (greetings) => {
    if ($.isArray(greetings)) {
        greetings.forEach((greeting) => {
            $("div#greetings").prepend(
                '<div class="alert alert-success" role="alert">' +
                '<span>' + greeting.message + '</span>' +
                '</div>'
            );
        });
    }
}

const pollServer = () => {
    window.setTimeout(() => {
        $.ajax({
            url: "/api/greetings",
            type: 'GET',
            data: {
                since: lastPollMillis
            },
            success: (response) => {
                lastPollMillis = getUTCEpocMillis();
                updateGreetings(response);
                pollServer();
            },
            error: () => {
                console.error("Poll failed");
                pollServer();
            }
        });
    }, 2000);
}

$().ready(() => {
    console.log("Starting server polling")
    pollServer();
});