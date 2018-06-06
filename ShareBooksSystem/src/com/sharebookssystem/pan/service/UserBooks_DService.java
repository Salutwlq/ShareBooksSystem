package com.sharebookssystem.pan.service;

import com.sharebookssystem.model.User;
import com.sharebookssystem.pan.myinterface.BorrowsDataService;
import com.sharebookssystem.pan.myinterface.CBorrowDataDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service("userBooks_DService")
public class UserBooks_DService implements BorrowsDataService {
    @Resource(name = "cBorrowDataDaoImpl")
    private CBorrowDataDao cBorrowDataDao;

    @Override
    public List service(User user, int page) {
        List list = cBorrowDataDao.queryB_BBYUID(user.getUserId(), page);
        if (list == null) return null;

        List<List> info = new ArrayList<List>();

        for (int i = 0; i < list.size(); i++){
            Object[] o = (Object[])list.get(i);

            List line = new ArrayList();
            String bookName = o[0].toString();

            System.out.println(bookName);

            line.add(bookName);
            String bookAuthor = o[1].toString();
            line.add(bookAuthor);
            String bookPublish = o[2].toString();
            line.add(bookPublish);
            String bookPrice = o[3].toString();
            line.add(bookPrice);
            String bookCategory = o[4].toString();
            line.add(bookCategory);
            int times = Integer.parseInt(o[5].toString());
            line.add(times);

            info.add(line);
        }

        return info;
    }

    public UserBooks_DService() {
    }

    public CBorrowDataDao getcBorrowDataDao() {
        return cBorrowDataDao;
    }

    public void setcBorrowDataDao(CBorrowDataDao cBorrowDataDao) {
        this.cBorrowDataDao = cBorrowDataDao;
    }
}
