import React, { Component } from 'react'
import Button from '@material-ui/core/Button';
import { bindActionCreators } from "redux";
import * as Actions from "../actions/Actions";
import { Link, withRouter } from "react-router-dom";
import connect from "react-redux/es/connect/connect";
import strings from "../localization";


class Forbidden extends Component {

    constructor(props) {
        super(props);

        this.props.changeFullScreen(true);
    }


    render() {

        return (

            <div id='forbidden'>
                <h1>401</h1>
                <h3>{strings.forbidden.forbidden}</h3>

                <Link to={'/'}>
                    <Button variant="contained" color="secondary">
                        {strings.forbidden.dashboard}
                    </Button>
                </Link>
            </div>
        );
    }
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({
        changeFullScreen: Actions.changeFullScreen
    }, dispatch);
}

function mapStateToProps({ menuReducers }) {
    return { menu: menuReducers };
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Forbidden));