import React from 'react';
import styled from 'styled-components';

const ResponsiveBlock = styled.div`
  padding: 0 1rem;
  margin: 0 auto;
  width: 1024px;

  @media (max-width: 1024px) {
    width: 768px;
  }

  @media (max-width: 768px) {
    width: 100%;
  }
`;

const Responsive = ({ children, ...rest }) => {
  return <ResponsiveBlock {...rest}>{children}</ResponsiveBlock>;
};
export default Responsive;
