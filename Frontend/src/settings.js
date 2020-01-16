const URLs = {
    "Home": "/",
    "Login": "/login",
    "Teacher": "/Teacher",
    "NoMatch": "*"
}

function URLSettings() {
    const getURL = (key) => { return URLs[key] }

    return {
        getURL
    }
}
export default URLSettings();


