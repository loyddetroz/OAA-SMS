import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import axios from 'axios';
import React, { Component } from 'react';
import Notification from '../Notification/Notification';

export default class Modal extends Component {
  constructor(props) {
    super(props);

    this.state = {
      input: '',
    };
  }

  handleModalDelete = () => {
    const { input } = this.state;
    const { onClose, idToBeDeleted } = this.props;

    if (idToBeDeleted == input) {
      this.delete(idToBeDeleted);
      onClose(false);
    }
  };

  handleModalCancel = () => {
    const { onClose } = this.props;
    onClose(false);
  };

  delay = (ms) => new Promise((res) => setTimeout(res, ms));

  delete = async (id) => {
    const {
      currentTable: innerTable,
      url,
      onRead,
      onShow,
      onMessage,
    } = this.props;
    const token = sessionStorage.getItem('token');
    const options = { headers: { Authorization: `Bearer ${token}` } };

    if (innerTable === 'donors') {
      try {
        console.log('HIT');
        await axios.delete(`${url}/donor/${id}`, options);
        onRead();
        await this.setNotif(
          onMessage,
          onShow,
          `Sucessfully deleted donor ID ${id}.`,
        );
      } catch (err) {
        await this.setNotif(onMessage, onShow, err.status);
      }
    } else if (innerTable === 'donations') {
      try {
        await axios.delete(`${url}/donation/${id}`, options);
        onRead();
        await this.setNotif(
          onMessage,
          onShow,
          `Sucessfully deleted donation ID ${id}.`,
        );
      } catch (err) {
        await this.setNotif(onMessage, onShow, err.status);
      }
    }
  };

  async setNotif(onMessage, onShow, message) {
    await onMessage(message);
    onShow(true);
    await this.delay(3000);
    onShow(false);
    await this.delay(1000);
    onShow('none');
  }

  render() {
    const { input } = this.state;

    const {
      title,
      message,
      leftBtnName,
      rightBtnName,
      deleteConfirmation,
    } = this.props;

    return (
      <div className="modal__background">
        <div className="deleteModal modal flex--vertical">
          <div className="modal__exit" onClick={this.handleModalCancel}>
            <FontAwesomeIcon icon="times" />
          </div>
          <div className="modal__icon">
            <FontAwesomeIcon icon="trash" size="3x" />
          </div>
          <p className="modal__title">{title}</p>
          <p className="modal__message">{message}</p>
          <p className="modal__message">{deleteConfirmation}</p>
          <input
            type="text"
            value={input}
            onChange={(e) => this.setState({ input: e.target.value })}
          />
          <div className="modal__btnContainer flex--horizontal">
            <button onClick={this.handleModalCancel}>{leftBtnName}</button>
            <button onClick={this.handleModalDelete}>{rightBtnName}</button>
          </div>
        </div>
      </div>
    );
  }
}