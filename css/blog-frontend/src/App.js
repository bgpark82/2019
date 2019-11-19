import React from 'react';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import PostList from './pages/PostList';
import RegisterPage from './pages/RegisterPage';
import WritePage from './pages/WritePage';
import PostPage from './pages/PostPage';

function App() {
  return (
    <>
      <Route path="/" exact component={PostList} />
      <Route path="/@:username" exact component={PostList} />
      <Route path="/login" component={LoginPage} />
      <Route path="/register" component={RegisterPage} />
      <Route path="/write" component={WritePage} />
      <Route path="/@:username/:postId" component={PostPage} />
    </>
  );
}

export default App;
