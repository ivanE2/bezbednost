import React from 'react'
import TablePage from "../../common/TablePage";
import { getUsers } from "../../services/UserAdminService";
import { bindActionCreators } from "redux";
import * as Actions from "../../actions/Actions";
import { withRouter } from "react-router-dom";
import connect from "react-redux/es/connect/connect";
import strings from "../../localization";
import AddUser from "./AddUser";
import { withSnackbar } from "notistack";
import { ListItemIcon, ListItemText, Menu, MenuItem, TableCell } from "@material-ui/core";
class UserList extends TablePage {

    tableDescription = [
        { key: 'email', label: strings.userList.email },
        { key: 'firstName', label: strings.userList.firstName },
        { key: 'lastName', label: strings.userList.lastName },
        { key: 'role', label: strings.userList.lastName },
    ];

    constructor(props) {
        super(props);

        this.state.showActions = false;
    }

    fetchData() {

        this.setState({
            lockTable: true
        });

        getUsers({
            page: this.state.searchData.page - 1,
            perPage: this.state.searchData.perPage,
            term: this.state.searchData.search.toLowerCase()
        }).then(response => {

            if (!response.ok) {
                return;
            }

            this.setState({
                tableData: response.data.entities,
                total: response.data.total,
                lockTable: false
            });
        });
    }

    componentDidMount() {
        this.fetchData();
    }

    getPageHeader() {
        return <h1>{strings.userList.pageTitle}</h1>;
    }

    renderAddContent() {
        return <AddUser onCancel={this.onCancel} onFinish={this.onFinish} />
    }




    renderRowMenu(index, item) {

        let ariaOwns = 'action-menu-' + index;

        return (
            <TableCell>

            </TableCell>
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

export default withSnackbar(withRouter(connect(mapStateToProps, mapDispatchToProps)(UserList)));