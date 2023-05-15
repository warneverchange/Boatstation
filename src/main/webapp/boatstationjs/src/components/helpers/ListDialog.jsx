import { Dialog, DialogActions, DialogContent, DialogTitle, InputLabel, List, ListItem, ListItemButton, ListItemText, MenuItem, Select } from "@mui/material";
import { useImperativeHandle, useState } from "react"
import DeleteIcon from '@mui/icons-material/Delete';
import IconButton from '@mui/material/IconButton';
import { Button } from "@mui/material";
import CloseIcon from '@mui/icons-material/Close';
import { forwardRef } from "react";
import { FormControl } from "@mui/material";
import { Document } from "docx";

export const ListDialog = forwardRef(({ title, getItems, getAllItems, deleteItem, showListItem, showSelectorItem, getListItemId, getSelectorItemId, addItem, onClose }, ref) => {
    const [isOpen, setOpen] = useState(false);
    const [selectedId, setSelectedId] = useState('');


    useImperativeHandle(ref, () => ({
        setOpen: (value) => {
            setOpen(value);
        }
    }));

    return (
        <Dialog
            open={isOpen}
            onClose={onClose}
            aria-labelledby="scroll-dialog-title"
            aria-describedby="scroll-dialog-description"
        >
            <DialogTitle id={"dialog-title-id-" + title}>
                {title}
                <IconButton onClick={onClose}>
                    <CloseIcon />
                </IconButton>
            </DialogTitle>
            <DialogContent dividers={true}>
                <List>
                    {
                        getItems().map((item) => {
                            return (
                                <ListItem>
                                    <ListItemText>{showListItem(item)}</ListItemText>
                                    <ListItemButton id={getListItemId(item)} onClick={deleteItem}>
                                        <IconButton>
                                            <DeleteIcon />
                                        </IconButton>
                                    </ListItemButton>
                                </ListItem>
                            )
                        })
                    }
                </List>

            </DialogContent>
            <DialogActions>

                <FormControl sx={{ m: 1, minWidth: 250 }}>
                    <InputLabel id="input-label">{title}</InputLabel>
                    <Select
                        labelId="input-label"
                        label={title}
                        // autoWidth
                        onChange={(event) => setSelectedId(event.target.value)}
                    >
                        {getAllItems().map((item) => {
                            return (
                                <MenuItem value={getSelectorItemId(item)}>{showSelectorItem(item)}</MenuItem>
                            )
                        })}
                    </Select>
                </FormControl>
                <Button id={selectedId} onClick={addItem}>Add item</Button>
            </DialogActions>
        </Dialog>
    )
})