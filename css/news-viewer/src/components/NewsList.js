import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import { NewsItem } from './NewsItem';
import Axios from '../../node_modules/axios/index';

const NewListBlock = styled.div``;

export const NewsList = () => {
  const [articles, setArticles] = useState(null);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);

      const res = await Axios.get(
        'https://newsapi.org/v2/top-headlines?' +
          'country=us&' +
          'apiKey=699183efbef2496ca8f121ea05a3ab23',
      );
      setArticles(res.data.articles);
      setLoading(false);
    };
    fetchData();
  }, []);

  if (loading) {
    return <NewListBlock>대기 중...</NewListBlock>;
  }
  if (!articles) {
    return null;
  }

  return (
    <NewListBlock>
      {articles.map(article => (
        <NewsItem key={article.url} article={article} />
      ))}
    </NewListBlock>
  );
};
