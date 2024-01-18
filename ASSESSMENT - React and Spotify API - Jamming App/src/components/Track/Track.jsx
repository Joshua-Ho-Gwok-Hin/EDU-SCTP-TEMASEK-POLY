import { useCallback } from "react";
import './Track.css'

function Track(props) {

    const addTrack = useCallback(
        (event) => {
            props.onAdd(props.track);
        },
        [props.onAdd, props.track]
    );

    const removeTrack = useCallback(
        (event) => {
            props.onRemove(props.track);
        },
        [props.onRemove, props.track]
    );

    const renderAction = () => {
        if (props.isRemoval) {
            return (
                <button className="flex-items flex-right track-button" onClick={removeTrack}>
                    -
                </button>
            );
        }
        return (
            <button className="flex-items flex-right track-button" onClick={addTrack}>
                +
            </button>
        );
    };

    return (
        <div className="trackcard">
            <div className="flex-items flex-left">
                <h4 className="track-name">{props.track.name}</h4>
                <p className="track-description">{props.track.artist} | {props.track.album}</p>
            </div>
            {renderAction()}
        </div>
    )
}

export default Track;