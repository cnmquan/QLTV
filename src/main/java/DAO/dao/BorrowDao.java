package DAO.dao;

import Base.iCRUD;
import Base.iDeleteHandler;
import Base.iGetTitle;
import Base.iValidation;
import DTO.Borrow;
import DTO.Publisher;

public interface BorrowDao extends iCRUD<Borrow>, iGetTitle, iValidation<Borrow>, iDeleteHandler<Borrow> {
}
