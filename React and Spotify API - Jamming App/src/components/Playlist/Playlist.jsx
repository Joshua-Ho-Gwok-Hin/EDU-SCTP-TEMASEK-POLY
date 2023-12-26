import React, { useCallback } from "react";
import TrackList from "../TrackList/TrackList";
import './Playlist.css';


function Playlist(props) {

    const handleNameChange = useCallback(
        ({ target }) => {
            props.onNameChange(target.value);
        },
        [props.onNameChange]
    );

    return (
        <div className="side-containers">
            <h3>Create Your New Playlist</h3>

            <input onChange={handleNameChange} className="playlist-input"  defaultValue={"New Playlist"} />
            <TrackList
                tracks={props.playlistTracks}
                isRemoval={true}
                onRemove={props.onRemove}
            />
            <button className="full-save-button" onClick={props.onSave}>
                SAVE TO SPOTIFY
            </button>

            <button className="full-redirect-button" onClick={() => { window.open('https://open.spotify.com', '_blank') }} >
                Go to my SPOTIFY account
            </button>
        </div>
    );
}

export default Playlist;