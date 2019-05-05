package vn.edu.hcmut.core.dao.hibernate;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.edu.hcmut.core.form.RegisterForm;
import vn.edu.hcmut.core.model.hibernate.Profiles;
import vn.edu.hcmut.core.repository.hibernate.IProfiles;
import vn.edu.hcmut.core.utilities.hibernate.HibernateGenericDao;

@Repository("profilesHibernateDAO")
@Transactional(rollbackOn = Exception.class)
public class ProfilesDAO extends HibernateGenericDao<Profiles,Long> implements IProfiles {

	@Autowired
	public ProfilesDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Profiles createNewProfile(RegisterForm formData) throws Exception {
		try {
			//Set object thongTinCaNhan
			Profiles ttcn = new Profiles();
			//CMND
			ttcn.setCmndMatSau(formData.getIdPassportImageBack());
			ttcn.setCmndMatTruoc(formData.getIdPassportImageFront());
			ttcn.setCmndNgayCap(formData.getIdPassportDate());
			ttcn.setCmndNoiCapId(Long.valueOf(formData.getIdPassportPlace()));
			ttcn.setCmndSo(formData.getIdPassport());
			//
			ttcn.setEmail_1(formData.getEmail());
			ttcn.setGioiTinh(formData.getGender());
			//HK
			ttcn.setDcttPhuongXaId(Long.valueOf(formData.getWardPlace()));
			ttcn.setDcttQuanHuyenId(Long.valueOf(formData.getDistrictPlace()));
			ttcn.setDcttSoNhaDuong(formData.getStreetPlace());
			ttcn.setDcttTinhTpId(Long.valueOf(formData.getCityPlace()));
			//Ho-TEN
			//Split fullname
			String p_hoVaTen = formData.getFullName().toUpperCase().trim();
			String p_hoLot = p_hoVaTen.substring(0, p_hoVaTen.lastIndexOf(" ")).trim();
			String p_ten = p_hoVaTen.substring(p_hoVaTen.lastIndexOf(" ")).trim();
			ttcn.setHoLot(p_hoLot);
			ttcn.setHoVaTen(p_hoVaTen);
			ttcn.setTen(p_ten);
			//
			if(formData.getBirthDate().length()==4) {
				ttcn.setNamSinh(formData.getBirthDate());
			}else {
				//Split date
				String p_ngayThangNamSinh = formData.getBirthDate();
				String p_ngaySinh = p_ngayThangNamSinh.substring(0, 2);
				String p_thangSinh = p_ngayThangNamSinh.substring(3, 5);
				String p_namSinh = p_ngayThangNamSinh.substring(6, 10);
				ttcn.setNgayThangNamSinh(p_ngayThangNamSinh);
				ttcn.setNamSinh(p_namSinh);
				ttcn.setNgaySinh(p_ngaySinh);
				ttcn.setThangSinh(p_thangSinh);
			}
			//NS
			ttcn.setNoiSinhTinhTpId(Long.valueOf(formData.getBirthPlace()));
			ttcn.setNoiSinhTinhTpKhac(formData.getBirthPlaceOther());
			//
			ttcn.setSoDienThoai(formData.getPhone());
			//
			this.save(ttcn);
			//
			return ttcn;
			
		}catch (Exception e) {
			throw e;
		}
	}

}
