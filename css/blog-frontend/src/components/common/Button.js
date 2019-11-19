import React from 'react';
import styled, { css } from 'styled-components';
import palette from '../../lib/style/palette';

const StyledButton = styled.button`
  background: ${palette.gray[8]};
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 1rem;
  padding: 0.24rem 1rem;
  font-weight: bold;
  outline: none;
  cursor: pointer;

  &:hover {
    background: ${palette.gray[6]};
  }

  ${props =>
    props.fullWidth &&
    css`
      padding-top: 0.75rem;
      padding-bottom: 0.75rem;
      width: 100%;
      font-size: 1.125rem;
    `}

  ${props =>
    props.cyan &&
    css`
      background: ${palette.cyan[5]};
      &:hover {
        background: ${palette.cyan[4]};
      }
    `}
`;

const Button = props => <StyledButton {...props} />;

export default Button;
