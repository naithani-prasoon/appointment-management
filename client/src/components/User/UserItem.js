import React from "react";
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import { Link } from "react-router-dom";

export default function UserItem(props) {

    return (
        <Link to={`/user/${props.user.id}`}>
            <Card sx={{ minWidth: 275 }} >
                <CardContent>
                    <Typography variant="h5" component="div">
                        {props.user.firstName + " " + props.user.lastName}
                    </Typography>
                </CardContent>
            </Card>
        </Link>
    )
}
