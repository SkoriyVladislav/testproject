import * as paths from "../../../../paths/config-paths";

const API = {
    sendRequest(path: string) {
        return fetch(`${paths.URL_SERVER}assets/i18n/${path}.json`, {
            method: 'GET',
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json; charset=utf-8',
            }
        }).then((response) => {
            if (response.status !== 200) {
                return Promise.reject();
            }
            return response.json();
        })
            .then((trans) => {
                this.changeLang(trans);
            })
            .catch((error) => {
                console.log('error', error)
            });
    }
};