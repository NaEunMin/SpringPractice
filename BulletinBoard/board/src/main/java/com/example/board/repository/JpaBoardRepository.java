package com.example.board.repository;

import com.example.board.model.Board;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaBoardRepository implements BoardRepository {
    private final EntityManagerFactory emf;

    public JpaBoardRepository() {
        this.emf = Persistence.createEntityManagerFactory("hello");
    }
    //트랜잭션은 데이터베이스에서 하나의 작업 단위
    //begin -> 트랜잭션 시작
    //commit -> 트랜잭션이 성공하여 데이터베이스에 반영
    //rollback -> 트랜잭션 실패 시 변경사항을 복구


    @Override
    public Board save(Board board) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(board);
            tx.commit();
            return board;
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
            em.close(); //Entity 매니저는 반드시 닫아야 함.
        }
    }

    @Override
    public List<Board> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("select b from Board b", Board.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Board findById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            //기본 키로 단일 게시글 조회
            return em.find(Board.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public void update(long id, Board updateBoard) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Board board = em.find(Board.class, id); //기존 게시글 id로 조회
            if (board != null) {
                //게시글 수정
                board.setTitle(updateBoard.getTitle());
                board.setContent(updateBoard.getContent());
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(em.find(Board.class, id));
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
            em.close();
        }

    }

    @Override
    public void deleteAll() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.createQuery("delete from Board").executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }
}
