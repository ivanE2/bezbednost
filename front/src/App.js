import React from 'react'
import { Router } from "react-router-dom";
import { createStore } from "redux";
import appReducers from "./reducers/Reducers";
import { loadUser } from "./actions/AuthActions";
import history from "./history";
import AuthWrapper from "./base/AuthWrapper";
import { getRoutes } from "./route";
import Provider from "react-redux/es/components/Provider";
import BaseLayout from "./base/BaseLayout";
import { MuiThemeProvider, createMuiTheme } from '@material-ui/core/styles';
import CssBaseline from '@material-ui/core/CssBaseline';
import { SnackbarProvider, withSnackbar } from 'notistack';

const theme = createMuiTheme({
  });

const store = createStore(appReducers);
store.dispatch(loadUser());

if ('ontouchstart' in document.documentElement) {
    document.body.style.cursor = 'pointer';
}

load();

const App = () => (

    <Provider store={store}>
        <Router history={history}>
            <SnackbarProvider maxSnack={3}>
                <AuthWrapper>
                    <MuiThemeProvider theme={theme}>
                        <CssBaseline>
                            <BaseLayout>
                                {
                                    getRoutes()
                                }
                            </BaseLayout>
                        </CssBaseline>

                    </MuiThemeProvider>
                </AuthWrapper>
            </SnackbarProvider>
        </Router>
    </Provider>
);

function load() {

}

export default App;
