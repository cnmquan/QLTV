package DAO.dao;

import Base.iCRUD;
import Base.iDeleteHandler;
import Base.iGetTitle;
import Base.iValidation;
import DTO.Reader;

public interface ReaderDao extends iCRUD<Reader>, iGetTitle, iValidation<Reader>, iDeleteHandler<Reader> {

}
