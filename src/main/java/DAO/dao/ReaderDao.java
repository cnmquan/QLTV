package db;

import model.Reader;

public interface ReaderDao extends iCRUD<Reader>, iGetTitle, iValidation<Reader>, iDeleteHandler<Reader> {

}
