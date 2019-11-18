import React, { useState } from 'react';
import Axios from 'axios';
import { NewsList } from './components/NewsList';

const App = () => {
  return (
    <div>
      <NewsList />
    </div>
  );
};

export default App;
