import React, { useState, useCallback } from 'react';

import logo from '../../logo.svg';
import './App.css';

import Playlist from '../Playlist/Playlist';
import SearchBar from '../SearchBar/SearchBar';
import SearchResults from '../SearchResults/SearchResults';
import Spotify from '../../utils/Spotify'

function App() {
  const [searchResults, setSearchResults] = useState([]);
  const [playlistName, setPlaylistName] = useState("New Playlist");
  const [playlistTracks, setPlaylistTracks] = useState([]);

  const search = useCallback((term) => {
    Spotify.search(term).then(setSearchResults);
  }, []);

  const addTrack = useCallback(
    (track) => {
      if (playlistTracks.some((savedTrack) => savedTrack.id === track.id))
        return;

      setPlaylistTracks((prevTracks) => [...prevTracks, track]);
    },
    [playlistTracks]
  );

  const removeTrack = useCallback((track) => {
    setPlaylistTracks((prevTracks) =>
      prevTracks.filter((currentTrack) => currentTrack.id !== track.id)
    );
  }, []);

  const updatePlaylistName = useCallback((name) => {
    setPlaylistName(name);
  }, []);

  const savePlaylist = useCallback(() => {
    const trackUris = playlistTracks.map((track) => track.uri);
    Spotify.savePlaylist(playlistName, trackUris).then(() => {
      setPlaylistName("New Playlist");
      setPlaylistTracks([]);
    });
  }, [playlistName, playlistTracks]);

  return (
    <div className="App">

      <header className="App-header">
        <div className='topbar'>
          <div className='container-780'>
            <nav className='container-flex'>
              <img src={logo} className="App-logo" alt="logo" />
              <title className='flex-items'>My Jamming Studio</title>
              <div className='flex-items'>
                <p>
                  Hi hello!
                </p>
              </div>
            </nav>
          </div>
        </div>
      </header>

      <div className='push-footer'>
        <SearchBar onSearch={search} />
        <div className='floating-box-container container-780'>
          <SearchResults searchResults={searchResults} onAdd={addTrack} />
          <Playlist
            playlistName={playlistName}
            playlistTracks={playlistTracks}
            onNameChange={updatePlaylistName}
            onRemove={removeTrack}
            onSave={savePlaylist}
          />
        </div>

      </div>

      <footer className='footer'>
        <div className='container-780 container-flex'>
          <p>
            Copyright &copy; 2023
          </p>
          <p>
            My Jamming Studio Pte Ltd
          </p>
          <p>All Rights Reserved</p>
        </div>
      </footer>

    </div>
  );
}

export default App;
