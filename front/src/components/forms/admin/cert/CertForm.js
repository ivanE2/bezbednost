import React from 'react';
import strings from "../../../../localization";
import { getError, hasError } from "../../../../functions/Validation";
import { Button, TextField } from "@material-ui/core";

const CertForm = ({
    onSubmit,
    onCancel,
    onChange,
    errors,
    data
}) => (

        <form id='user-form'>
            <TextField
                label={'Issuer name'}
                error={hasError(errors, 'issuerName')}
                helperText={getError(errors, 'issuerName')}
                fullWidth
                autoFocus
                name='issuerName'
                onChange={onChange}
                margin="normal"
                value={data.issuerName}
            />
            <TextField
                label={'Name'}
                error={hasError(errors, 'name')}
                helperText={getError(errors, 'name')}
                fullWidth
                autoFocus
                name='name'
                onChange={onChange}
                margin="normal"
                value={data.name}
            />
            <TextField
                label={'Serial Number'}
                error={hasError(errors, 'serialNumber')}
                helperText={getError(errors, 'serialNumber')}
                fullWidth
                autoFocus
                name='serialNumber'
                onChange={onChange}
                margin="normal"
                value={data.serialNumber}
            />
            <TextField
                label={'Start Date'}
                error={hasError(errors, 'startDate')}
                helperText={getError(errors, 'startDate')}
                fullWidth
                autoFocus
                name='startDate'
                onChange={onChange}
                margin="normal"
                value={data.startDate}
            />
            <TextField
                label={'End Date'}
                error={hasError(errors, 'endDate')}
                helperText={getError(errors, 'endDate')}
                fullWidth
                autoFocus
                name='endDate'
                onChange={onChange}
                margin="normal"
                value={data.endDate}
            />
            <div className='submit-container'>
                <Button variant="contained" color="primary" onClick={onSubmit}>
                    {strings.userForm.ok}
                </Button>
                <Button variant="contained" color="secondary" onClick={onCancel}>
                    {strings.userForm.cancel}
                </Button>
            </div>
        </form>
    );

export default CertForm;